package com.github.zavier.ems.orgmng.org.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.org.OrgRepository
import org.springframework.stereotype.Component

@Component
class OrgNameValidator(private val orgRepository: OrgRepository) {

    // 组织必须有名称
    fun shouldNotEmpty(name: String) {
        if (name.isBlank()) {
            throw BusinessException("组织没有名称！")
        }
    }

    // 同一个组织下的下级组织不能重名
    fun shouldNotDuplicatedInSameSuperior(tenantId: Long, superiorId: Long, name: String) {
        if (orgRepository.existsBySuperiorIdAndName(tenantId, superiorId, name)) {
            throw BusinessException("同一上级下已经有名为'$name'的组织存在！")
        }
    }
}
