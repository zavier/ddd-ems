package com.github.zavier.ems.tenantmng

import com.github.zavier.ems.common.framework.AuditableEntity
import java.time.LocalDateTime

class Tenant(createdAt: LocalDateTime, createdBy: Long) : AuditableEntity(createdAt, createdBy) {
    val id: Long? = null
    var name: String = ""
    var status: TenantStatus = TenantStatus.EFFECTIVE
}
