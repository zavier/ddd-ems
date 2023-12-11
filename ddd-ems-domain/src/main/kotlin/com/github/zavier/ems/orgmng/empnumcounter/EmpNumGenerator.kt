package com.github.zavier.ems.orgmng.empnumcounter

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class EmpNumGenerator(private val empNumCounterRepository: EmpNumCounterRepository) {

    fun generateEmpNum(tenantId: Long): String {
        return (generateEmpNumByYear(tenantId, LocalDate.now().year))
    }

    private fun generateEmpNumByYear(tenantId: Long, yearNum: Int): String {
        val maxNum: Int = empNumCounterRepository.increaseMaxNumByYear(tenantId, yearNum)
        return String.format("%04d%08d", yearNum, maxNum)
    }
}
