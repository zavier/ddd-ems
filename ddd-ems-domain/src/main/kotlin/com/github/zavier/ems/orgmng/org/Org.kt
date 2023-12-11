package com.github.zavier.ems.orgmng.org

import com.github.zavier.ems.common.framework.AuditableEntity
import com.github.zavier.ems.common.framework.BusinessException
import java.time.LocalDateTime

class Org(val tenantId: Long, val orgTypeCode: String, createdAt: LocalDateTime, createdBy: Long) :
    AuditableEntity(createdAt, createdBy) {

    var id: Long = 0
    var superiorId: Long = 0
    var leaderId: Long = 0
    var name: String = ""
    var status: OrgStatus = OrgStatus.EFFECTIVE

    val isEffective: Boolean
        get() = status == OrgStatus.EFFECTIVE

    val isCanceled: Boolean
        get() = status == OrgStatus.CANCELLED

    fun cancel() {
        this.status = OrgStatus.CANCELLED
    }
}

enum class OrgStatus(private val code: String, val desc: String) {
    EFFECTIVE("EF", "有效"),
    CANCELLED("CA", "终止");

    fun code(): String {
        return code
    }

    companion object {
        fun ofCode(code: String): OrgStatus {
            return entries.firstOrNull { it.code == code }
                ?: throw BusinessException("$code 不是有效的组织状态代码！")
        }
    }
}
