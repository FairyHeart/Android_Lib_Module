package com.fairy.module.enums

/**
 * 请求状态枚举值
 *
 * @author: Fairy.
 * @date  : 2020/12/16.
 *
 * @property SUCCESS 加载成功
 * @property COMPLETE 加载完成（失败或者成功都会调用）
 * @property FAIL 加载失败
 * @property LOADING 加载中
 */
enum class StatusEnum {
    SUCCESS,
    COMPLETE,
    FAIL,
    LOADING
}