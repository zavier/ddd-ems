package com.github.zavier.ems.orgmng.org.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.orgtype.OrgTypeRepository
import com.github.zavier.ems.orgmng.orgtype.OrgTypeStatus
import org.springframework.stereotype.Component

@Component
class OrgTypeValidator(private val orgTypeRepository: OrgTypeRepository) {

    // 组织类别不能为空
    fun shouldNotEmpty(orgType: String) {
        if (orgType.isBlank()) {
            throw BusinessException("组织类别不能为空！")
        }
    }

    // 组织类别必须有效
    fun shouldEffective(tenant: Long, orgType: String) {
        if (!orgTypeRepository.existsByCodeAndStatus(tenant, orgType, OrgTypeStatus.EFFECTIVE)) {
            throw BusinessException("'$orgType'不是有效的组织类别代码！")
        }
    }

    // 企业是在创建租户的时候创建好的，因此不能单独创建企业
    fun shouldNotEntp(orgType: String) {
        if ("ENTP" == orgType) {
            throw BusinessException("企业是在创建租户的时候创建好的，因此不能单独创建企业!")
        }
    }
}
