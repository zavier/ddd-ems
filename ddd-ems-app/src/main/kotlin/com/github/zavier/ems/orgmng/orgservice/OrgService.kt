package com.github.zavier.ems.orgmng.orgservice

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.org.Org
import com.github.zavier.ems.orgmng.org.OrgBuilderFactory
import com.github.zavier.ems.orgmng.org.OrgHandler
import com.github.zavier.ems.orgmng.org.OrgRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class OrgService(
    private val orgBuilderFactory: OrgBuilderFactory,
    private val orgRepository: OrgRepository,
    val orgHandler: OrgHandler
) {


    @Transactional
    fun addOrg(request: CreateOrgRequest, userId: Long): OrgResponse {
        val builder = orgBuilderFactory.create()

        var org: Org = builder.tenantId(request.tenantId)
            .orgTypeCode(request.orgTypeCode)
            .leaderId(request.leaderId)
            .superiorId(request.superiorId)
            .name(request.name)
            .createdBy(userId)
            .build()

        org = orgRepository.save(org)

        return buildOrgDto(org)
    }

    @Transactional
    fun updateOrgBasic(id: Long, request: UpdateOrgBasicRequest, userId: Long): OrgResponse {
        val org: Org = orgRepository.findById(request.tenantId, id)
            ?: throw BusinessException("要修改的组织(id=$id)不存在！");

        orgHandler.updateBasic(org, request.name, request.leaderId, userId)
        orgRepository.update(org)

        return buildOrgDto(org)
    }

    @Transactional
    fun cancelOrg(tenant: Long, id: Long, userId: Long): Long {
        val org: Org = orgRepository.findById(tenant, id)
            ?: throw BusinessException("要取消的组织(id =$id  )不存在！")

        orgHandler.cancel(org, userId)
        orgRepository.update(org)

        return org.id
    }

    private fun buildOrgDto(org: Org): OrgResponse {
        val response = OrgResponse(
            org.id,
            org.tenantId,
            org.superiorId,
            org.orgTypeCode,
            org.leaderId,
            org.name,
            org.status.code(),
            org.createdAt,
            org.createdBy,
            org.lastUpdatedAt,
            org.lastUpdatedBy
        )
        return response
    }

}

data class UpdateOrgBasicRequest(var tenantId: Long, var leaderId: Long, var name: String)

data class CreateOrgRequest(
    val tenantId: Long,
    val superiorId: Long,
    val orgTypeCode: String,
    val leaderId: Long,
    val name: String
)

data class OrgResponse(
    val id: Long,
    val talentId: Long,
    val superiorId: Long,
    val orgTypeCode: String,
    val leaderId: Long,
    val name: String,
    val statusCode: String,
    val createdAt: LocalDateTime,
    val createdBy: Long,
    val lastUpdatedAt: LocalDateTime,
    val lastUpdatedBy: Long
)