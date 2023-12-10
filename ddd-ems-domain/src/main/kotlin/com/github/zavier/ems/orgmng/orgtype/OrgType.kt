package com.github.zavier.ems.orgmng.orgtype


import com.github.zavier.ems.common.framework.AuditableEntity
import com.github.zavier.ems.common.framework.BusinessException
import java.time.LocalDateTime

class OrgType(createdAt: LocalDateTime, createdBy: Long) : AuditableEntity(createdAt, createdBy) {
    var code: String? = null
    var tenant: Long = 0
    var name: String? = null
    var status: OrgTypeStatus? = null
}

enum class OrgTypeStatus(private val code: String, val desc: String) {
    EFFECTIVE("EF", "有效"),
    TERMINATED("TE", "终止");

    fun code(): String {
        return code
    }

    companion object {
        fun ofCode(code: String): OrgTypeStatus {
            return entries.firstOrNull { it.code == code }
                ?: throw BusinessException("$code 不是有效的组织类型状态代码！")
        }
    }
}
