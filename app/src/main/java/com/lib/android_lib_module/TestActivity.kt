package com.lib.android_lib_module

import android.os.Bundle
import com.fairy.module.activity.RootActivity

class TestActivity : RootActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
    }

    override fun initViewData(savedInstanceState: Bundle?) {
    }
}