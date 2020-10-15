//package com.fairy.module.observer
//
//import android.content.Context
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//
//
///**
// * 返回结果统一处理
// *
// * @author: Fairy.
// * @date  : 2020/10/14.
// */
//abstract class ResultObserver<T>(
//    private val context: Context,
//    private val loading: MutableLiveData<Boolean>? = null,
//    private val toast: MutableLiveData<String>? = null
//) : Observer<ResultDto<T>> {
//    /**
//     * Called when the data is changed.
//     * @param it  The new data
//     */
//    override fun onChanged(it: ResultDto<T>?) {
//        loading?.value = false
//        if (it == null) {
//            return
//        }
//        if (!it.isSuccess()) {
//            this.onFailure(it.errorCode, it.message)
//            toast?.value = it.message
//            return
//        }
//        onSuccess(it.data)
//    }
//
//
//    abstract fun onSuccess(value: T?)
//
//    open fun onFailure(errorCode: String?, errorMessage: String?) {}
//}