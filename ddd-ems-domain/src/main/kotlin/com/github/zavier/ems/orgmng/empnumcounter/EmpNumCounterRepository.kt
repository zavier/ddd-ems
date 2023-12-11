package com.github.zavier.ems.orgmng.empnumcounter

interface EmpNumCounterRepository {
    fun save(empNumCounter: EmpNumCounter)

    fun findByYear(tenantId: Long, yearNum: Int): EmpNumCounter?

    fun increaseMaxNumByYear(tenantId: Long, yearNum: Int): Int
}
