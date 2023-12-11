package com.github.zavier.ems.restful.orgmng

import com.github.zavier.ems.orgmng.orgservice.CreateOrgRequest
import com.github.zavier.ems.orgmng.orgservice.OrgResponse
import com.github.zavier.ems.orgmng.orgservice.OrgService
import com.github.zavier.ems.orgmng.orgservice.UpdateOrgBasicRequest
import org.springframework.web.bind.annotation.*

@RestController
class OrgController(private val orgService: OrgService) {
    @PostMapping("/api/organizations")
    fun addOrg(
        @RequestParam("userid") userId: Long,
        @RequestBody request: CreateOrgRequest
    ): OrgResponse {
        return orgService.addOrg(request, userId)
    }

    @PatchMapping("/api/organizations/{id}")
    fun updateOrgBasic(
        @PathVariable id: Long,
        @RequestParam("userid") userId: Long,
        @RequestBody request: UpdateOrgBasicRequest
    ): OrgResponse {
        return orgService.updateOrgBasic(id, request, userId)
    }


    @PostMapping("/api/organizations/{id}/cancel")
    fun cancelOrg(
        @PathVariable id: Long,
        @RequestParam("userid") userId: Long,
        @RequestParam tenant: Long
    ): Long {
        //Long user = acquireUserId();
        return orgService.cancelOrg(tenant, id, userId)
    }

    private fun acquireUserId(): Long {
        //TODO: get from header/cookie/session
        return 1L
    }
}
