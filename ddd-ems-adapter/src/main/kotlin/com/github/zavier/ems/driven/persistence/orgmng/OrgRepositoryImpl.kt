package com.github.zavier.ems.driven.persistence.orgmng

import com.github.zavier.ems.orgmng.org.Org
import com.github.zavier.ems.orgmng.org.OrgRepository
import com.github.zavier.ems.orgmng.org.OrgStatus
import org.springframework.stereotype.Repository

@Repository
open class OrgRepositoryImpl() : OrgRepository {
    override fun save(org: Org): Org {
        TODO("Not yet implemented")
    }

    override fun findById(tenantId: Long, id: Long): Org? {
        TODO("Not yet implemented")
    }

    override fun findByIdAndStatus(tenantId: Long, id: Long, status: OrgStatus): Org? {
        TODO("Not yet implemented")
    }

    override fun existsBySuperiorIdAndName(tenant: Long, superior: Long, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun existsByIdAndStatus(tenantId: Long, orgId: Long, effective: OrgStatus): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(org: Org): Int {
        TODO("Not yet implemented")
    }
}