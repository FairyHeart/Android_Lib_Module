package com.lib.android_lib_module

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.fairy.module.ui.activity.BindActivity

abstract class ActionBarActivity<T : ViewDataBinding, VM : ViewModel>(layoutId: Int) :
    BindActivity<T, VM>(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = this.supportActionBar

        actionBar?.setCustomView(R.layout.actionbar)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        //去掉阴影
        if (Build.VERSION.SDK_INT >= 21) {
            actionBar?.elevation = 0.toFloat()
        }

    }


}