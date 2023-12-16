package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.AuditableEntity
import com.github.zavier.ems.common.valueobject.Period
import java.time.LocalDateTime

class WorkExperience(
    val tenantId: Long,
    val period: Period,
    var company: String,
    createdAt: LocalDateTime,
    createdBy: Long
) : AuditableEntity(createdAt, createdBy) {

    val id: Long? = null

    fun getWorkPeriod(): Period {
        return this.period
    }

    internal fun setCompany(company: String): WorkExperience {
        this.company = company
        return this
    }
}