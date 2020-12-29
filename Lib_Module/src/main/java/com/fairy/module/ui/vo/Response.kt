package com.fairy.module.ui.vo

import com.fairy.module.enums.StatusEnum

/**
 * 将网络调用的状态传给UI层
 *
 * @author: Fairy.
 * @date  : 2020/12/16.
 *
 * @param status 状态
 * @param data 数据
 * @param code 异常code
 * @param msg 提示或者异常信息
 */
class Response<out T>(
    val status: StatusEnum,
    val data: T?,
    val code: String? = null,
    val msg: String? = null
) {
    companion object {
        fun <T> success(data: T?, msg: String? = null) =
            Response(StatusEnum.SUCCESS, data, null, msg)

        fun fail(
            code: String? = null,
            msg: String?
        ) =
            Response(StatusEnum.FAIL, null, code, msg)

        fun loading(msg: String? = null) = Response(StatusEnum.LOADING, null, null, msg)

        fun complete() = Response(StatusEnum.COMPLETE, null)
    }
}