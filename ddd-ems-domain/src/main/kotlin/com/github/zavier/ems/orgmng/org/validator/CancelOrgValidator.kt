package com.github.zavier.ems.orgmng.org.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.emp.EmpRepository
import com.github.zavier.ems.orgmng.emp.EmpStatus
import com.github.zavier.ems.orgmng.org.Org
import org.springframework.stereotype.Component

@Component
class CancelOrgValidator(private val empRepository: EmpRepository) {

    // 要被撤销的组织不能有下属员工
    fun shouldNotHasEmp(tenant: Long, id: Long) {
        if (empRepository.existByIdAndStatus(tenant, id, EmpStatus.PROBATION, EmpStatus.REGULAR)) {
            throw BusinessException("该组织中仍然有员工，不能撤销！")
        }
    }

    // 只有有效组织才能被撤销
    fun shouldEffective(org: Org) {
        if (!org.isEffective) {
            throw BusinessException("该组织不是有效状态，不能撤销！")
        }
    }
}
