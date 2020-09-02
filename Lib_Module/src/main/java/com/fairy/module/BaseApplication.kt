package com.fairy.module

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
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
        this.initARouter()
    }

    private fun initPlatform() {
        //初始化平台配置
        AppPlatform.setDebug(debug())
            .setContext(this)
            .setVersionName(this.getAppVersionName() ?: "unKnow")
            .setVersionCode(this.getAppVersionCode())
    }

    private fun initARouter() {
        //初始化路由ARouter
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (debug()) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    abstract fun debug(): Boolean
}