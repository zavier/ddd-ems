package com.github.zavier.ems.repository.orgmng

import com.github.zavier.ems.orgmng.orgtype.OrgType
import com.github.zavier.ems.orgmng.orgtype.OrgTypeRepository
import com.github.zavier.ems.orgmng.orgtype.OrgTypeStatus
import org.springframework.stereotype.Repository

@Repository
open class OrgTypeRepositoryImpl() : OrgTypeRepository {
    override fun existsByCodeAndStatus(tenant: Long, code: String, status: OrgTypeStatus): Boolean {
        TODO("Not yet implemented")
    }

    override fun findByCodeAndStatus(tenantId: Long, code: String, status: OrgTypeStatus): OrgType? {
        TODO("Not yet implemented")
    }
}