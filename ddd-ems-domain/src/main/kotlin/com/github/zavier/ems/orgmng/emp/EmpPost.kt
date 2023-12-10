package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.AuditableEntity
import java.time.LocalDateTime

class EmpPost(createdAt: LocalDateTime, createdBy: Long) : AuditableEntity(createdAt, createdBy) {
    var postCode: String? = null
}