package com.fairy.module

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.fairy.lib.utils.getAppVersionCode
import com.fairy.lib.utils.getAppVersionName

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/9/1.
 */
abstract class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        this.initPlatform()
    }

    private fun initPlatform() {
        //初始化平台配置
        AppPlatform.setDebug(debug())
            .setContext(this)
            .setVersionName(this.getAppVersionName() ?: "unKnow")
            .setVersionCode(this.getAppVersionCode())
    }

    abstract fun debug(): Boolean
}