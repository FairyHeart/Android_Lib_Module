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
    }

    override fun refresh() {
    }

}