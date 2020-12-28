package com.fairy.module.ktx

import android.view.KeyEvent
import com.fairy.lib.utils.manager.ActivityManager


private var exitTime: Long = 0

/**
 * 再按一次退出app
 *
 * @param keyCode
 * @param time 两次返回的时间间隔 默认为2秒
 */
fun KeyEvent?.exitApp(keyCode: Int, time: Int = 2000): Boolean {
    if (keyCode == KeyEvent.KEYCODE_BACK && this?.action == KeyEvent.ACTION_DOWN) {
        return if ((System.currentTimeMillis() - exitTime) > time) {
            exitTime = System.currentTimeMillis()
            false
        } else {
            ActivityManager.INSTANCE.finishAllActivity()
            true
        }
    }
    return false
}