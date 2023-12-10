package com.github.zavier.ems.orgmng.org

import com.github.zavier.ems.common.validator.CommonTenantValidator
import com.github.zavier.ems.orgmng.org.validator.OrgLeaderValidator
import com.github.zavier.ems.orgmng.org.validator.OrgNameValidator
import com.github.zavier.ems.orgmng.org.validator.OrgTypeValidator
import com.github.zavier.ems.orgmng.org.validator.SuperiorValidator
import org.springframework.stereotype.Component

@Component
class OrgBuilderFactory(
    private val commonTenantValidator: CommonTenantValidator,
    private val orgTypeValidator: OrgTypeValidator,
    private val superiorValidator: SuperiorValidator,
    private val orgNameValidator: OrgNameValidator,
    private val orgLeaderValidator: OrgLeaderValidator) {

    fun create(): OrgBuilder {
        return OrgBuilder(
            commonTenantValidator,
            orgTypeValidator,
            superiorValidator,
            orgNameValidator,
            orgLeaderValidator
        )
    }

}