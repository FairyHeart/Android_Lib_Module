package com.fairy.module.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.fairy.lib.utils.kfunc.inflateLayout
import com.fairy.module.AppPlatform
import com.fairy.module.observer.LoadingObserver
import com.fairy.module.observer.ToastObserver

/**
 * 基础Fragment
 *
 * @author: Fairy.
 * @date  : 2020/9/1.
 */
abstract class RootFragment : Fragment() {

    val mContext = AppPlatform.context

    /**
     * 加载对话框显示值LiveData
     */
    val loadingState by lazy {
        MutableLiveData<Boolean>()
    }

    /**
     * 异常提示值LiveData
     */
    val toastState by lazy {
        MutableLiveData<String?>()
    }

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
        this.loadingState.observe(viewLifecycleOwner, LoadingObserver(activity))
        this.toastState.observe(viewLifecycleOwner, ToastObserver(activity))
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