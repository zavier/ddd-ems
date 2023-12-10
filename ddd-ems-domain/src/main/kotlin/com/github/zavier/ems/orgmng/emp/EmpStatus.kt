package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.BusinessException
import java.util.*

enum class EmpStatus(val code: String) {
    REGULAR("REG"),  // 正式
    PROBATION("PRO"),  // 试用期
    TERMINATED("TER"); // 终止

    fun code(): String {
        return code
    }

    fun becomeRegular(): EmpStatus {
        if (this != PROBATION) {
            throw BusinessException("试用期员工才能转正！")
        }
        return REGULAR
    }

    fun terminate(): EmpStatus {
        if (this == TERMINATED) {
            throw BusinessException("已经终止的员工不能再次终止！")
        }
        return TERMINATED
    }

    companion object {
        fun ofCode(code: String): EmpStatus {
            return Arrays.stream(entries.toTypedArray()).filter { v: EmpStatus -> v.code == code }.findAny()
                .orElseThrow {
                    BusinessException(
                        "员工状态代码错误！"
                    )
                }
        }
    }
}