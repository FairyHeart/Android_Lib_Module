package com.fairy.module

import android.content.Context
import com.fairy.module.exception.handler.IExceptionHandler

/**
 * app平台配置
 *
 * @author: Fairy.
 * @date  : 2019-08-06.
 */
object AppPlatform {

    /**
     * 是否测试环境
     */
    var debug: Boolean = false
        private set

    /**
     * app版本名字
     */
    lateinit var versionName: String
        private set

    /**
     * app版本号
     */
    var versionCode: Int = 0

    /**
     * app异常处理类
     */
    lateinit var exceptionHandler: IExceptionHandler

    /**
     * 全局context
     */
    lateinit var context: Context

    fun setDebug(debug: Boolean): AppPlatform {
        AppPlatform.debug = debug
        return this
    }

    fun setVersionName(versionName: String): AppPlatform {
        AppPlatform.versionName = versionName
        return this
    }

    fun setVersionCode(versionCode: Int): AppPlatform {
        AppPlatform.versionCode = versionCode
        return this
    }

    fun setExceptionHandler(exceptionHandler: IExceptionHandler): AppPlatform {
        AppPlatform.exceptionHandler = exceptionHandler
        return this
    }

    fun setContext(context: Context): AppPlatform {
        AppPlatform.context = context
        return this
    }

}