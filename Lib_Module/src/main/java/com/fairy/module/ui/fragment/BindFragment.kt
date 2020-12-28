package com.fairy.module.ui.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fairy.module.dialog.LoadingDialog
import com.fairy.module.enums.MsgLevelEnum
import com.fairy.module.enums.StatusEnum
import com.fairy.module.ui.ILifecycleView
import com.fairy.module.ui.vm.BaseAndroidViewModel
import java.lang.reflect.ParameterizedType

/**
 * 顶层fragment
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
abstract class BindFragment<T : ViewDataBinding, VM : BaseAndroidViewModel>(
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

        //绑定加载中和异常提示对话框
        vm.loadStateLvd.observe(viewLifecycleOwner) {
            when (it.status) {
                StatusEnum.LOADING -> loading(it.msg)
                StatusEnum.SUCCESS -> loadSuccess()
                StatusEnum.FAIL -> loadFail(it.code, it.msg)
                StatusEnum.COMPLETE -> loadComplete()
            }
        }
        vm.msgStateLvd.observe(viewLifecycleOwner) {
            showMsg(it.level, it.msg)
        }
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

    override fun loadFail(code: String?, msg: String?) {
        msg?.let {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun loading(msg: String?) {
        LoadingDialog.builder()
            .setLoadingText(msg ?: "loading")
            .build(mActivity)
            .showDialog()
    }

    override fun showMsg(level: MsgLevelEnum, msg: String?) {
        msg?.let {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}