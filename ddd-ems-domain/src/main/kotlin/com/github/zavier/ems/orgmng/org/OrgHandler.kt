package com.github.zavier.ems.orgmng.org

import com.github.zavier.ems.orgmng.org.validator.CancelOrgValidator
import com.github.zavier.ems.orgmng.org.validator.OrgLeaderValidator
import com.github.zavier.ems.orgmng.org.validator.OrgNameValidator
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OrgHandler(
    private val assertOrgName: OrgNameValidator,
    private val assertOrgLeader: OrgLeaderValidator,
    private val assertOrgToBeCanceled: CancelOrgValidator
) {

    fun updateBasic(org: Org, newName: String, newLeader: Long, userId: Long) {
        updateName(org, newName)
        updateLeader(org, newLeader)
        updateAuditInfo(org, userId)
    }

    fun cancel(org: Org, userId: Long) {
        assertOrgToBeCanceled.shouldNotHasEmp(org.tenantId, org.id)
        assertOrgToBeCanceled.shouldEffective(org)
        org.cancel()
        updateAuditInfo(org, userId)
    }

    private fun updateLeader(org: Org, newLeader: Long) {
        if (newLeader != org.leaderId) {
            assertOrgLeader.shouldEffective(org.tenantId, newLeader)
            org.leaderId = newLeader
        }
    }

    private fun updateName(org: Org, newName: String) {
        if (newName != org.name) {
            assertOrgName.shouldNotEmpty(newName)
            assertOrgName.shouldNotDuplicatedInSameSuperior(org.tenantId, org.superiorId, newName)
            org.name = newName
        }
    }

    private fun updateAuditInfo(org: Org, userId: Long) {
        org.setLastUpdatedBy(userId)
        org.setLastUpdatedAt(LocalDateTime.now())
    }
}
