package com.lib.android_lib_module

import android.os.Bundle
import androidx.lifecycle.Observer
import com.fairy.module.dialog.LoadingDialog
import com.fairy.module.ui.activity.BindActivity
import com.lib.android_lib_module.databinding.ActivityMainBinding
import com.lib.android_lib_module.vm.MainViewModel

class MainActivity : BindActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    val dialog = LoadingDialog.builder()
        .setMinDelay(500)
        .setMinShowTime(500)
        .setStyle(R.style.Theme_AppCompat_Light_Dialog)
        .setLoadingText("加载中...")
        .build(this)


    override fun initObserver() {
        loadingStateLvd.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            if (it) {
                dialog.showDialog()
            } else {
                dialog.hideDialog()
            }
        })
    }

    override fun setVariable() {
        binding.activity = this
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        super.initViewData(savedInstanceState)
        dialog.hideDialog()
    }
    fun btn1() {
        loadingStateLvd.value = true
        Thread {
            Thread.sleep(300)
            loadingStateLvd.postValue(false)
        }.start()
    }

    fun btn2() {
        loadingStateLvd.value = true
        Thread {
            Thread.sleep(10000)
            loadingStateLvd.postValue(false)
        }.start()
    }
}