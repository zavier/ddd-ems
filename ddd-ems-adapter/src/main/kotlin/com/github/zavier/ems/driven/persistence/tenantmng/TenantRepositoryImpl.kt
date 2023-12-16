package com.github.zavier.ems.driven.persistence.tenantmng

import com.github.zavier.ems.tenantmng.TenantRepository
import com.github.zavier.ems.tenantmng.TenantStatus
import org.springframework.stereotype.Repository

@Repository
open class TenantRepositoryImpl : TenantRepository {

    override fun existsByIdAndStatus(tenantId: Long, status: TenantStatus): Boolean {
        TODO("Not yet implemented")
    }

}
