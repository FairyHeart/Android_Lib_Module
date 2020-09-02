package com.fairy.module.exception.handler

/**
 * 异常处理接口
 *
 * @author: Fairy.
 * @date  : 2019-08-06.
 */
interface IExceptionHandler {

    /**
     * 异常处理
     */
    fun handlerException(t: Throwable, upload: Boolean = false)

}