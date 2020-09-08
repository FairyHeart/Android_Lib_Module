package com.fairy.module.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fairy.lib.utils.kfunc.inflateLayout
import com.fairy.module.AppPlatform

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/9/1.
 */
abstract class RootFragment : Fragment() {

    val mContext = AppPlatform.context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return context?.inflateLayout(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initView()
        this.initViewData(savedInstanceState)
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