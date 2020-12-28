package com.fairy.module.ui.vo

import com.fairy.module.enums.MsgLevelEnum

/**
 * 加载状态
 *
 * @author: Fairy.
 * @date  : 2020/12/28.
 */
class MsgState(
    val level: MsgLevelEnum,
    val msg: String?
) {
    companion object {

        fun info(msg: String?) = MsgState(MsgLevelEnum.INFO, msg)

        fun error(msg: String?) = MsgState(MsgLevelEnum.ERROR, msg)

        fun tip(msg: String?) = MsgState(MsgLevelEnum.TIP, msg)

    }
}