package com.lib.android_lib_module

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.fairy.module.ktx.exitApp
import com.fairy.module.ui.activity.BindActivity
import com.fairy.module.ui.vo.LoadState
import com.lib.android_lib_module.databinding.ActivityTestBinding
import com.lib.android_lib_module.vm.TestViewModel

class TestActivity : BindActivity<ActivityTestBinding, TestViewModel>(R.layout.activity_test) {

    private lateinit var testFragment: TestFragment


    override fun initObserver() {

    }

    override fun setVariable() {
        binding.vm = vm
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        //显示Fragment
        this.testFragment = TestFragment()
//        this.showFragment(testFragment, R.id.frame_layout)

        vm.loadStateLvd.value = LoadState.loading();

    }

    override fun refresh() {
    }


    /**
     * 再按一次退出程序提示
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (event.exitApp(keyCode)) {
            true
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            false
        }
    }

}