package com.lib.android_lib_module

import android.os.Bundle
import com.fairy.module.ui.fragment.BindFragment
import com.lib.android_lib_module.databinding.FragmentTestBinding
import com.lib.android_lib_module.vm.TestFragmentViewModel


class TestFragment :
    BindFragment<FragmentTestBinding, TestFragmentViewModel>(R.layout.fragment_test) {


    override fun initObserver() {

    }

    override fun setVariable() {
        binding.vm = vm
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        //显示加载中对话框
        loadingState.value = true
        //显示异常提示框
        toastState.value = "网络异常"
    }

    override fun refresh() {
    }

}