package com.fairy.module.observer

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.fairy.module.dialog.ILoadingDialog
import com.fairy.module.dialog.LoadingDialog

/**
 * 网络请求加载中提示框
 *
 * @author: Fairy.
 * @date  : 2020/10/14.
 */
class LoadingObserver private constructor() : Observer<Boolean> {

    private lateinit var dialog: ILoadingDialog

    /**
     * 使用自定义的对话框
     */
    constructor(dialog: ILoadingDialog) : this() {
        this.dialog = dialog
    }

    /**
     * 使用默认的提示框，支持修改相关样式
     */
    constructor(
        mActivity: FragmentActivity?,
        minShowTime: Int = 0,//最小显示时长
        minDelay: Int = 0,//延迟时间
        style: Int = 0,//对话框样式
        loadingText: String? = null
    ) : this() {

        dialog = LoadingDialog.builder()
            .setMinDelay(minDelay)
            .setMinShowTime(minShowTime)
            .setStyle(style)
            .setLoadingText(loadingText)
            .build(mActivity)
    }

    override fun onChanged(t: Boolean?) {
        if (t == null) {
            return
        }
        if (t) {
            dialog.showDialog()
        } else {
            dialog.hideDialog()
        }
    }
}