package com.fairy.module.ktx

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.fairy.module.exception.BizException
import com.fairy.module.ui.vm.BaseAndroidViewModel
import com.fairy.module.ui.vo.LoadState
import com.fairy.module.ui.vo.Response
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
 *
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
 * BaseAndroidViewModel 协程拓展函数
 *
 * @param context
 * @param start
 * @param loadingTxt 加载中提示文案
 * @param block 调用接口
 */
fun BaseAndroidViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
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
                loadStateLvd.value = LoadState.fail(e.code, e.message)
            } else {
                loadStateLvd.value = LoadState.fail(null, e.message)
            }
        } finally {
            loadStateLvd.value = LoadState.complete()
        }
    }
}

/**
 * BaseAndroidViewModel LiveData拓展函数
 *
 * @param loadingTip 加载中提示内容
 * @param block 函数体
 */
fun <T> BaseAndroidViewModel.liveDataResponse(
    loadingTip: String? = null,
    block: suspend () -> T
): LiveData<Response<T>> {
    return liveData {
        emit(Response.loading(loadingTip))
        try {
            emit(Response.success(block.invoke()))
        } catch (e: Throwable) {
            if (e is BizException) {
                emit(Response.fail(e.code, e.message))
            } else {
                emit(Response.fail(msg = e.message))
            }
        } finally {
            emit(Response.complete())
        }
    }
}

/**
 * BaseAndroidViewModel LiveData拓展函数，加载中状态、异常状态、加载完成状态自动处理
 *
 * @param loadingTip 加载中提示内容
 * @param block 函数体
 */
fun <T> BaseAndroidViewModel.liveDataT(
    loadingTip: String? = null,
    block: suspend () -> T
): LiveData<T> {
    return liveData {
        loadStateLvd.value = LoadState.loading(loadingTip)
        try {
            emit(block.invoke())
        } catch (e: Throwable) {
            if (e is BizException) {
                loadStateLvd.value = LoadState.fail(e.code, e.message)
            } else {
                loadStateLvd.value = LoadState.fail(null, e.message)
            }
        } finally {
            loadStateLvd.value = LoadState.complete()
        }
    }
}