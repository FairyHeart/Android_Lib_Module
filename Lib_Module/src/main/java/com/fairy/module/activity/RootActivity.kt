package com.fairy.module.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fairy.lib.utils.manager.ActivityManager

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/9/1.
 */
abstract class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.INSTANCE.addActivity(this)
    }

    /**
     * 获取布局文件
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initViewData(savedInstanceState: Bundle?)
}