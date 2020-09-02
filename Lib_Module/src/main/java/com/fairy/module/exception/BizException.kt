package com.fairy.module.exception

/**
 * 业务异常
 *
 * @author: Fairy.
 * @date  : 2019-08-06.
 */
class BizException @JvmOverloads constructor(
    val code: String? = null,
    override val message: String?
) : RuntimeException() {
    constructor(code: String? = null, t: Throwable) : this(code, t.message)
}