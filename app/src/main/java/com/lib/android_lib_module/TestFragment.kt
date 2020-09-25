package com.lib.android_lib_module

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fairy.module.fragment.RootFragment


class TestFragment : RootFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_test
    }

    override fun initView() {
    }

    override fun initViewData(savedInstanceState: Bundle?) {
    }

}