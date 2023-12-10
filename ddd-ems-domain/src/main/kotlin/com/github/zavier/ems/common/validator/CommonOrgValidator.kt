package com.github.zavier.ems.common.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.org.OrgRepository
import com.github.zavier.ems.orgmng.org.OrgStatus
import org.springframework.stereotype.Component

@Component
class CommonOrgValidator(val orgRepository: OrgRepository) {
    fun shouldValid(tenantId: Long, orgId: Long) {
        if (!orgRepository.existsByIdAndStatus(tenantId, orgId, OrgStatus.EFFECTIVE)) {
            throw BusinessException("id为'$orgId'的组织不是有效组织！")
        }
    }
}