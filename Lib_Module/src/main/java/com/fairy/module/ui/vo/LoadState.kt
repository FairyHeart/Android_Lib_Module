package com.fairy.module.ui.vo

import com.fairy.module.enums.StatusEnum

/**
 * 加载状态
 *
 * @author: Fairy.
 * @date  : 2020/12/28.
 *
 * @param status 状态
 * @param code 异常code
 * @param msg 提示消息或者异常信息
 */
class LoadState(
    val status: StatusEnum,
    val code: String? = null,
    val msg: String? = null
) {
    companion object {

        fun success() = LoadState(StatusEnum.SUCCESS)

        fun fail(code: String? = null, msg: String?) =
            LoadState(StatusEnum.FAIL, code, msg)

        fun loading(msg: String? = null) = LoadState(StatusEnum.LOADING, msg = msg)

        fun complete() = LoadState(StatusEnum.COMPLETE)
    }
}