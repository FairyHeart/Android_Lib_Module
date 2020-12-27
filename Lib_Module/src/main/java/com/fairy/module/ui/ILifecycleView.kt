package com.fairy.module.ui

import android.os.Bundle

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/18.
 */
interface ILifecycleView {

    /**
     * 绑定界面数据
     */
    fun initObserver()

    /**
     * 绑定DataBinding数据
     */
    fun setVariable()

    /**
     * 初始化界面
     */
    fun initView() {}

    /**
     * 初始化界面数据
     */
    fun initViewData(savedInstanceState: Bundle?) {}

    /**
     * 刷新数据(界面重新可见的时候调用)
     */
    fun refresh() {}
}