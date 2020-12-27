package com.fairy.module.ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * ViewModel拓展函数
 *
 * @author: Fairy.
 * @date  : 2020/11/27.
 */
fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(context, start) {
        try {
            block.invoke(this)
        } catch (e: Throwable) {
            onError.invoke(e)
        } finally {
            onComplete.invoke()
        }
    }
}
