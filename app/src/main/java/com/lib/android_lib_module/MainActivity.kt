package com.lib.android_lib_module

import android.os.Bundle
import com.fairy.module.dialog.LoadingDialog
import com.lib.android_lib_module.databinding.ActivityMainBinding
import com.lib.android_lib_module.vm.MainViewModel

class MainActivity : ActionBarActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    val dialog = LoadingDialog.builder()
        .setMinDelay(500)
        .setMinShowTime(500)
        .setStyle(R.style.Theme_AppCompat_Light_Dialog)
        .setLoadingText("加载中...")
        .build(this)


    override fun initObserver() {

    }

    override fun setVariable() {
        binding.activity = this
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        super.initViewData(savedInstanceState)
        dialog.hideDialog()
    }

    fun btn1() {
    }

    fun btn2() {

    }
}