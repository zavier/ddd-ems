package com.github.zavier.ems.orgmng.orgtype

interface OrgTypeRepository {
    fun existsByCodeAndStatus(tenant: Long, code: String, status: OrgTypeStatus): Boolean

    fun findByCodeAndStatus(tenantId: Long, code: String, status: OrgTypeStatus): OrgType?
}