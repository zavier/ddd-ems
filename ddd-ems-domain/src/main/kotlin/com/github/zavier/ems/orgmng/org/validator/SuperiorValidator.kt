package com.github.zavier.ems.orgmng.org.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.common.framework.DirtyDataException
import com.github.zavier.ems.orgmng.org.Org
import com.github.zavier.ems.orgmng.org.OrgRepository
import com.github.zavier.ems.orgmng.org.OrgStatus
import com.github.zavier.ems.orgmng.orgtype.OrgType
import com.github.zavier.ems.orgmng.orgtype.OrgTypeRepository
import com.github.zavier.ems.orgmng.orgtype.OrgTypeStatus
import org.springframework.stereotype.Component

// 领域服务
@Component
class SuperiorValidator(private val orgTypeRepository: OrgTypeRepository,
                        private val orgRepository: OrgRepository) {

    // 上级组织应该是有效组织
    fun shouldEffective(tenant: Long, superior: Long): Org {
        return orgRepository.findByIdAndStatus(
            tenant,
            superior, OrgStatus.EFFECTIVE
        ) ?: throw BusinessException("'$superior' 不是有效的组织 id !")
    }

    fun orgTypeShouldEffective(tenant: Long, superior: Long, superiorOrg: Org): OrgType {
        return orgTypeRepository.findByCodeAndStatus(
            tenant,
            superiorOrg.orgTypeCode,
            OrgTypeStatus.EFFECTIVE
        ) ?: throw DirtyDataException("id 为 '$superior' 的组织的组织类型代码 '${superiorOrg.orgTypeCode}' 无效!")
    }

    // 开发中心和直属部门的上级只能是企业
    fun ofDevCenterAndDirectDeptMustEntp(superior: Long, orgType: String, superiorOrgType: OrgType) {
        if (("DEVCENT" == orgType || "DIRDEP" == orgType)
            && "ENTP" != superiorOrgType.code
        ) {
            throw BusinessException(
                "开发中心或直属部门的上级(id = '$superior')不是企业！"
            )
        }
    }

    // 开发组的上级只能是开发中心
    fun ofDevGroupMustDevCenter(superior: Long, orgType: String, superiorOrgType: OrgType) {
        if ("DEVGRP" == orgType && "DEVCENT" != superiorOrgType.code) {
            throw BusinessException(
                "开发组的上级(id = '$superior')不是开发中心！"
            )
        }
    }
}
