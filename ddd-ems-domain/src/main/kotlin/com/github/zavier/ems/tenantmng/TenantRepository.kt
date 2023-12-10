package com.github.zavier.ems.tenantmng

interface TenantRepository {
    fun existsByIdAndStatus(tenantId: Long, status: TenantStatus): Boolean
}