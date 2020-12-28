package com.fairy.module.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.fairy.lib.utils.manager.ActivityManager
import com.fairy.module.enums.MsgLevelEnum
import com.fairy.module.enums.StatusEnum
import com.fairy.module.ui.ILifecycleView
import com.fairy.module.ui.vm.BaseAndroidViewModel
import java.lang.reflect.ParameterizedType

/**
 * 基础Activity
 *
 * @author: Fairy.
 * @date  : 2020/12/27.
 */
abstract class BindActivity<T : ViewDataBinding, VM : BaseAndroidViewModel>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity(), ILifecycleView {

    var activityTag: String? = null
        private set

    lateinit var binding: T

    lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTag = this.javaClass.name

        this.initActionBar()

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        this.initViewModel()
        this.initObserver()
        this.setVariable()
        this.initView()
        this.initViewData(savedInstanceState)

        //绑定加载中和异常提示对话框
        vm.loadStateLvd.observe(this) {
            when (it.status) {
                StatusEnum.LOADING -> loading(it.msg)
                StatusEnum.SUCCESS -> loadSuccess()
                StatusEnum.FAIL -> loadFail(it.code, it.msg)
                StatusEnum.COMPLETE -> loadComplete()
            }
        }
        //消息绑定
        vm.msgStateLvd.observe(this) {
            showMsg(it.level, it.msg)
        }
        ActivityManager.INSTANCE.addActivity(this)
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

    /**
     * 初始化ActionBar
     */
    open fun initActionBar() {}

    /**
     * 加载失败调用
     * @param code 异常码
     * @param msg 异常信息
     */
    override fun loadFail(code: String?, msg: String?) {
        msg?.let {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    /* override fun loading(msg: String?) {
         LoadingDialog.builder()
             .setLoadingText(msg ?: "loading")
             .build(this)
             .showDialog()
     }*/

    /**
     * 消息展示
     * @param level 消息级别
     * @param msg 消息内容
     */
    override fun showMsg(level: MsgLevelEnum, msg: String?) {
        msg?.let {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
