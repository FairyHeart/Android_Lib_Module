package com.fairy.module.observer

import android.app.ProgressDialog
import android.content.Context
import androidx.lifecycle.Observer

/**
 * 网络请求加载中进度提示框
 *
 * @author: Fairy.
 * @date  : 2020/10/14.
 */
class LoadingObserver(val context: Context?) : Observer<Boolean> {

    private val dialog: ProgressDialog? = null

    override fun onChanged(t: Boolean?) {
        if (t == null) {
            return
        }
        if (t) {
            dialog?.show()
        } else {
            dialog?.dismiss()
        }
    }
}