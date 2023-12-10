package com.github.zavier.ems.orgmng.org.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.emp.EmpRepository
import com.github.zavier.ems.orgmng.emp.EmpStatus
import org.springframework.stereotype.Component

@Component
class OrgLeaderValidator (private val empRepository: EmpRepository) {

    // 组织负责人可以空缺，如果有的话，的必须是一个在职员工（含试用期）
    fun shouldEffective(tenant: Long, leader: Long) {
        if (!empRepository.existByIdAndStatus(tenant, leader, EmpStatus.REGULAR, EmpStatus.PROBATION)) {
            throw BusinessException("组织负责人(id='$leader')不是在职员工！")
        }
    }
}
