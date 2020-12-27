package com.lib.android_lib_module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.fairy.module.ui.activity.BindActivity

abstract class BackgroundActivity<T : ViewDataBinding, VM : ViewModel>(layoutId: Int) :
    BindActivity<T, VM>(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun setContentView(layoutResID: Int) {
        val rootView = this.inflateLayout(R.layout.activity_base)
        val containerFlyt = rootView.findViewById<FrameLayout>(R.id.body_layout)
        containerFlyt.addView(this.inflateLayout(layoutResID))
        super.setContentView(rootView)
    }

    override fun setContentView(view: View?) {
        val rootView = this.inflateLayout(R.layout.activity_base)
        val containerFlyt = rootView.findViewById<FrameLayout>(R.id.body_layout)
        containerFlyt.addView(view)
        super.setContentView(rootView)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        val rootView = this.inflateLayout(R.layout.activity_base)
        val containerFlyt = rootView.findViewById<FrameLayout>(R.id.body_layout)
        containerFlyt.addView(view)
        super.setContentView(rootView, params)
    }


}