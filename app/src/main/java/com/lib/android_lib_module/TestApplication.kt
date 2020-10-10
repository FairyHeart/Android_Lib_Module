package com.lib.android_lib_module

import com.fairy.module.AppPlatform
import com.fairy.module.BaseApplication
import com.fairy.module.BuildConfig

class TestApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //设置异常处理类
        AppPlatform.setExceptionHandler(TextExceptionHandler())
    }

    override fun debug(): Boolean {
        return BuildConfig.DEBUG
    }

}