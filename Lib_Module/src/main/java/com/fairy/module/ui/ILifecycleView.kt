package com.fairy.module.ui

import android.os.Bundle
import com.fairy.module.enums.MsgLevelEnum

/**
 * 公用接口
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

    /**
     * 加载失败调用
     * @param code 异常码
     * @param msg 异常信息
     */
    fun loadFail(code: String? = null, msg: String? = null) {}

    /**
     * 加载中回调
     * @param msg 提示信息
     */
    fun loading(msg: String? = null) {}

    /**
     * 加载成功回调
     */
    fun loadSuccess() {}

    /**
     * 加载完成，失败或者成功都会回调
     */
    fun loadComplete() {}

    /**
     * 消息展示
     * @param level 消息级别
     * @param msg 消息内容
     */
    fun showMsg(level: MsgLevelEnum, msg: String?)
}