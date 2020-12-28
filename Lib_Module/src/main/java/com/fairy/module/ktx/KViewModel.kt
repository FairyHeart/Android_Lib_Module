package com.fairy.module.ktx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fairy.module.exception.BizException
import com.fairy.module.ui.vo.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * ViewModel拓展函数
 *
 * @author: Fairy.
 * @date  : 2020/11/27.
 */

/**
 * ViewModel 协程拓展函数
 * @param context
 * @param start
 * @param onError 加载失败回调
 * @param onComplete 加载完成回调
 * @param block 调用接口
 */
fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    onError: (e: Throwable, errorCode: String?) -> Unit = { _: Throwable, _: String? -> },
    onComplete: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(context, start) {
        try {
            block.invoke(this)
        } catch (e: Throwable) {
            if (e is BizException) {
                onError.invoke(e, e.code)
            } else {
                onError.invoke(e, null)
            }
        } finally {
            onComplete.invoke()
        }
    }
}

/**
 * ViewModel 协程拓展函数
 * @param context
 * @param start
 * @param loadStateLvd 加载状态LiveData
 * @param loadingTxt 加载中提示文案
 * @param block 调用接口
 */
fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    loadStateLvd: MutableLiveData<LoadState>,
    loadingTxt: String? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(context, start) {
        try {
            loadStateLvd.value = LoadState.loading(loadingTxt)
            block.invoke(this)
            loadStateLvd.value = LoadState.success()
        } catch (e: Throwable) {
            if (e is BizException) {
                loadStateLvd.value = LoadState.error(e.message, e.code)
            } else {
                loadStateLvd.value = LoadState.error(e.message)
            }
        } finally {
            loadStateLvd.value = LoadState.complete()
        }
    }
}