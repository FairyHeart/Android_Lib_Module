package com.lib.android_lib_module

import android.os.Bundle
import android.view.KeyEvent
import com.fairy.module.exitApp
import com.fairy.module.ui.activity.BindActivity
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

        //显示加载中对话框
        loadingStateLvd.value = true
        //显示异常提示框
        toastStateLvd.value = "网络异常"

        Thread {
            Thread.sleep(800)
            loadingStateLvd.postValue(false)
        }.start()
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
            toastStateLvd.value = "再按一次退出程序"
            false
        }
    }

}