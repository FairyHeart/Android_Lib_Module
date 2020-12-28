package com.fairy.module.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fairy.module.ui.vo.LoadState
import com.fairy.module.ui.vo.MsgState

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/10/15.
 */
abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * 加载数据LiveData
     */
    val loadStateLvd = MutableLiveData<LoadState>()

    /**
     * 提示消息LiveData
     */
    val msgStateLvd = MutableLiveData<MsgState>()
}