package com.lib.android_lib_module

import android.os.Bundle
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fairy.module.observer.LoadingObserver
import com.fairy.module.observer.ToastObserver
import com.fairy.module.ui.ILifecycleView
import java.lang.reflect.ParameterizedType

/**
 * 基础Acitivity
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
abstract class BindActivity2<T : ViewDataBinding, VM : ViewModel>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity(), ILifecycleView {

    var activityTag: String? = null
        private set

    lateinit var binding: T

    lateinit var vm: VM

    /**
     * 加载对话框显示值LiveData
     */
    val loadingStateLvd by lazy {
        MutableLiveData<Boolean>()
    }

    /**
     * 异常提示值LiveData
     */
    val toastStateLvd by lazy {
        MutableLiveData<String?>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTag = this.javaClass.name
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        binding = DataBindingUtil.setContentView(this, layoutId)
        window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        this.initViewModel()
        this.initObserver()
        this.setVariable()
        this.initView()
        this.initViewData(savedInstanceState)

        //绑定加载中和异常提示对话框
        this.loadingStateLvd.observe(this, LoadingObserver(this))
        this.toastStateLvd.observe(this, ToastObserver(this))

    }

    override fun onResume() {
        super.onResume()
        this.refresh()
    }

    private fun initViewModel() {
        val viewModelClass = getViewModelClass()
        if (viewModelClass != null) {
            vm = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            ).get(viewModelClass)
        }
    }

    private fun getViewModelClass(): Class<VM>? {
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType?
        val types = parameterizedType?.actualTypeArguments
        return if (types?.get(1) == null) null else types[1] as Class<VM>
    }
}
