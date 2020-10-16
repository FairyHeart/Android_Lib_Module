package com.lib.android_lib_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fairy.module.dialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val loadingState = MutableLiveData<Boolean>()

    val dialog = LoadingDialog.builder()
        .setMinDelay(500)
        .setMinShowTime(500)
        .setStyle(R.style.Theme_AppCompat_Light_Dialog)
        .setLoadingText("加载中...")
        .build(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            loadingState.value = true
            Thread {
                Thread.sleep(300)
                loadingState.postValue(false)
            }.start()
        }
        button2.setOnClickListener {
            loadingState.value = true
            Thread {
                Thread.sleep(10000)
                loadingState.postValue(false)
            }.start()
        }

        loadingState.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            if (it) {
                dialog.showDialog()
            } else {
                dialog.hideDialog()
            }
        })
    }
}