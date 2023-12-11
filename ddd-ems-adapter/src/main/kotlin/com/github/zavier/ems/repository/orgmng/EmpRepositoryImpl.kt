package com.github.zavier.ems.repository.orgmng

import com.github.zavier.ems.orgmng.emp.Emp
import com.github.zavier.ems.orgmng.emp.EmpRepository
import com.github.zavier.ems.orgmng.emp.EmpStatus
import org.springframework.stereotype.Repository

@Repository
open class EmpRepositoryImpl() : EmpRepository {
    override fun save(emp: Emp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun existByIdAndStatus(tenant: Long, id: Long, vararg statuses: EmpStatus): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(tenantId: Long, id: Long): Emp? {
        TODO("Not yet implemented")
    }
}