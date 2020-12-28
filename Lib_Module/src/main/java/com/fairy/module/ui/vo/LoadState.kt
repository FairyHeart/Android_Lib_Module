package com.fairy.module.ui.vo

import com.fairy.module.enums.StatusEnum

/**
 * 加载状态
 *
 * @author: Fairy.
 * @date  : 2020/12/28.
 */
class LoadState(
    val status: StatusEnum,
    val code: String? = null,
    val msg: String? = null
) {
    companion object {

        fun success() = LoadState(StatusEnum.SUCCESS)

        fun error(msg: String?, code: String? = null) =
            LoadState(StatusEnum.FAIL, msg, code)

        fun loading(msg: String? = null) = LoadState(StatusEnum.LOADING, msg = msg)

        fun complete() = LoadState(StatusEnum.COMPLETE)
    }
}