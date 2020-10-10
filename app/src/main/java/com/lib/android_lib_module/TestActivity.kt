package com.lib.android_lib_module

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.fairy.module.activity.RootActivity
import com.fairy.module.exitApp

class TestActivity : RootActivity() {

    private lateinit var testFragment: TestFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
    }

    override fun initViewData(savedInstanceState: Bundle?) {
        //显示Fragment
        this.testFragment = TestFragment()
        this.showFragment(testFragment, R.id.frame_layout)
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