package com.fairy.module.ui.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fairy.module.observer.LoadingObserver
import com.fairy.module.observer.ToastObserver
import com.fairy.module.ui.ILifecycleView
import java.lang.reflect.ParameterizedType

/**
 * 顶层fragment
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
abstract class BindFragment<T : ViewDataBinding, VM : ViewModel>(
    @LayoutRes val layoutId: Int
) : Fragment(), ILifecycleView {

    lateinit var mContext: Context
    lateinit var mActivity: FragmentActivity
    lateinit var mApplication: Application

    var fragmentActivity: FragmentActivity? = null

    var fragmentTag: String? = null
        private set

    lateinit var binding: T
    lateinit var vm: VM

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
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.fragmentTag = this.javaClass.name

        binding.lifecycleOwner = this
        binding.executePendingBindings()

        this.initViewModel()
        this.initObserver()
        this.setVariable()
        this.initView()
        this.initViewData(savedInstanceState)

        this.loadingState.observe(viewLifecycleOwner, LoadingObserver(activity))
        this.toastState.observe(viewLifecycleOwner, ToastObserver(activity))
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (context != null) {
            if (!hidden) {
                this.refresh()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        fragmentActivity = this.activity
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity as FragmentActivity
        mApplication = activity.application
    }

    private fun initViewModel() {
        val viewModelClass = getViewModelClass()
        if (viewModelClass != null) {
            vm = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(mApplication)
            ).get(viewModelClass)
        }
    }

    private fun getViewModelClass(): Class<VM>? {
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType?
        val types = parameterizedType?.actualTypeArguments
        return if (types?.get(1) == null) null else types[1] as Class<VM>
    }
}