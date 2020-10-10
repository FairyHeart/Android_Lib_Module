package com.fairy.module.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fairy.lib.utils.manager.ActivityManager

/**
 * 顶层Activity
 *
 * @author: Fairy.
 * @date  : 2020/9/1.
 */
abstract class RootActivity : AppCompatActivity() {

    /**
     * 当前fragment
     */
    var currentFragment: Fragment? = null

    /**
     * fragment集合
     */
    var fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.INSTANCE.addActivity(this)
    }

    /**
     * 显示Fragment
     *
     * @param fragment 显示fragment
     * @param containerViewId 存放fragment的布局Id，只有当第一次添加的时候使用
     * @param removeOldFragment 显示新的fragment的时候，是否移除原来的fragment true = 移除，false = 隐藏
     */
    fun showFragment(
        fragment: Fragment?,
        containerViewId: Int,
        removeOldFragment: Boolean = false
    ) {
        if (fragment == null) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        if (!fragments.contains(fragment)) {
            transaction.add(containerViewId, fragment)
            fragments.add(fragment)
        }
        if (currentFragment != null) {
            if (removeOldFragment) {
                transaction.remove(currentFragment!!)
                fragments.remove(currentFragment!!)
            } else {
                transaction.hide(currentFragment!!)
            }
        }
        transaction.show(fragment)
        transaction.commit()
        currentFragment = fragment
    }

    /**
     * 获取布局文件
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initViewData(savedInstanceState: Bundle?)
}