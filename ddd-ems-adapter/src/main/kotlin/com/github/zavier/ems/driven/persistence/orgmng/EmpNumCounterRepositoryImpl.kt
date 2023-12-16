package com.github.zavier.ems.driven.persistence.orgmng

import com.github.zavier.ems.orgmng.empnumcounter.EmpNumCounter
import com.github.zavier.ems.orgmng.empnumcounter.EmpNumCounterRepository
import org.springframework.stereotype.Repository

@Repository
open class EmpNumCounterRepositoryImpl() : EmpNumCounterRepository {
    override fun save(empNumCounter: EmpNumCounter) {
        TODO("Not yet implemented")
    }

    override fun findByYear(tenantId: Long, yearNum: Int): EmpNumCounter? {
        TODO("Not yet implemented")
    }

    override fun increaseMaxNumByYear(tenantId: Long, yearNum: Int): Int {
        TODO("Not yet implemented")
    }
}