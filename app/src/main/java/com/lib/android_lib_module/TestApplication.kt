package com.lib.android_lib_module

import com.fairy.module.AppPlatform
import com.fairy.module.BaseApplication
import com.fairy.module.BuildConfig

class TestApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        AppPlatform.setExceptionHandler(TextExceptionHandler())
    }

    override fun debug(): Boolean {
        return BuildConfig.DEBUG
    }

}