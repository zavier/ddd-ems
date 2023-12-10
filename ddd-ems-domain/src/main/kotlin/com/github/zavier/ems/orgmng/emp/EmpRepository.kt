package com.github.zavier.ems.orgmng.emp

interface EmpRepository {
    fun save(emp: Emp?): Boolean

    fun existByIdAndStatus(tenant: Long, id: Long, vararg statuses: EmpStatus): Boolean

    fun findById(tenantId: Long, id: Long): Emp?
}
