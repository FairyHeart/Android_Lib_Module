package com.lib.android_lib_module

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.fairy.module.activity.RootActivity
import com.fairy.module.exitApp

class TestActivity : RootActivity() {

    private lateinit var testFragment: TestFragment

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        //显示Fragment
        this.testFragment = TestFragment()
        this.showFragment(testFragment, R.id.frame_layout)

        //显示加载中对话框
        loadingState.value = true
        //显示异常提示框
        toastState.value = "网络异常"

        Thread {
            Thread.sleep(800)
            loadingState.postValue(false)
        }.start()
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