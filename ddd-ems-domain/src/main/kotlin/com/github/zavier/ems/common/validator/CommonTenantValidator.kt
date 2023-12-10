package com.github.zavier.ems.common.validator

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.tenantmng.TenantRepository
import com.github.zavier.ems.tenantmng.TenantStatus
import org.springframework.stereotype.Component

@Component
class CommonTenantValidator(val tenantRepository: TenantRepository) {

    // 领域层的校验，依赖了repository（repository接口定义在domain层，没有直接依赖外部实现）
    fun shouldEffective(tenant: Long) {
        // 租户必须有效
        if (!tenantRepository.existsByIdAndStatus(tenant, TenantStatus.EFFECTIVE)) {
            throw BusinessException("id为'$tenant'的租户不是有效租户！")
        }
    }
}