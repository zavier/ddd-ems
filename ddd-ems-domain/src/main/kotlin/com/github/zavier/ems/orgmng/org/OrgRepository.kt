package com.github.zavier.ems.orgmng.org

interface OrgRepository {
    fun save(org: Org): Org

    fun findById(tenantId: Long, id: Long): Org?

    fun findByIdAndStatus(tenantId: Long, id: Long, status: OrgStatus): Org?

    fun existsBySuperiorIdAndName(tenant: Long, superior: Long, name: String): Boolean

    fun existsByIdAndStatus(tenantId: Long, orgId: Long, effective: OrgStatus): Boolean

    fun update(org: Org): Int
}
