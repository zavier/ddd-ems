package com.github.zavier.ems.orgmng.org


import com.github.zavier.ems.common.validator.CommonTenantValidator
import com.github.zavier.ems.orgmng.org.validator.OrgLeaderValidator
import com.github.zavier.ems.orgmng.org.validator.OrgNameValidator
import com.github.zavier.ems.orgmng.org.validator.OrgTypeValidator
import com.github.zavier.ems.orgmng.org.validator.SuperiorValidator
import com.github.zavier.ems.orgmng.orgtype.OrgType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OrgBuilder internal constructor(
    private val assertOrgTenant: CommonTenantValidator,
    private val assertOrgType: OrgTypeValidator,
    private val assertSuperior: SuperiorValidator,
    private val assertOrgName: OrgNameValidator,
    private val assertOrgLeader: OrgLeaderValidator
) {

    private var tenantId: Long = 0
    private var superiorId: Long = 0
    private var orgTypeCode: String = ""
    private var leaderId: Long = 0
    private var name: String = ""
    private var createdBy: Long = 0


    fun tenantId(tenantId: Long): OrgBuilder {
        this.tenantId = tenantId
        return this
    }

    fun superiorId(superiorId: Long): OrgBuilder {
        this.superiorId = superiorId
        return this
    }

    fun orgTypeCode(orgTypeCode: String): OrgBuilder {
        this.orgTypeCode = orgTypeCode
        return this
    }

    fun leaderId(leaderId: Long): OrgBuilder {
        this.leaderId = leaderId
        return this
    }

    fun name(name: String): OrgBuilder {
        this.name = name
        return this
    }

    fun createdBy(createdBy: Long): OrgBuilder {
        this.createdBy = createdBy
        return this
    }

    fun build(): Org {
        validate()

        val org = Org(tenantId, orgTypeCode, LocalDateTime.now(), createdBy)
        org.leaderId = this.leaderId
        org.name = this.name
        org.superiorId = this.superiorId

        return org
    }

    private fun validate() {
        validateOrgTenant()
        validateOrgLeader()
        validateOrgType()
        validateSuperior()
        validateOrgName()
    }

    private fun validateOrgLeader() {
        assertOrgLeader.shouldEffective(tenantId, leaderId)
    }

    private fun validateOrgTenant() {
        assertOrgTenant.shouldEffective(tenantId)
    }

    private fun validateOrgName() {
        assertOrgName.shouldNotEmpty(name)
        assertOrgName.shouldNotDuplicatedInSameSuperior(tenantId, superiorId, name)
    }

    private fun validateSuperior() {
        val superiorOrg: Org = assertSuperior.shouldEffective(tenantId, superiorId)
        val superiorOrgType: OrgType = assertSuperior.orgTypeShouldEffective(tenantId, superiorId, superiorOrg)
        assertSuperior.ofDevGroupMustDevCenter(superiorId, orgTypeCode, superiorOrgType)
        assertSuperior.ofDevCenterAndDirectDeptMustEntp(superiorId, orgTypeCode, superiorOrgType)
    }

    private fun validateOrgType() {
        assertOrgType.shouldNotEmpty(orgTypeCode)
        assertOrgType.shouldEffective(tenantId, orgTypeCode)
        assertOrgType.shouldNotEntp(orgTypeCode)
    }
}
