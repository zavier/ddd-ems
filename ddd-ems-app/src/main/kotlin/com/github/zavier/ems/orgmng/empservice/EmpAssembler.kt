package com.github.zavier.ems.orgmng.empservice

import com.github.zavier.ems.common.framework.ChangingStatus
import com.github.zavier.ems.common.validator.CommonOrgValidator
import com.github.zavier.ems.common.valueobject.Period
import com.github.zavier.ems.orgmng.emp.Emp
import com.github.zavier.ems.orgmng.emp.EmpStatus
import com.github.zavier.ems.orgmng.emp.Gender
import com.github.zavier.ems.orgmng.emp.SkillLevel
import com.github.zavier.ems.orgmng.empnumcounter.EmpNumGenerator
import org.springframework.stereotype.Component

@Component
class EmpAssembler(private val assertOrg: CommonOrgValidator,
                   private val empNumGenerator: EmpNumGenerator) {
    fun toEmp(request: CreateEmpRequest, userId: Long): Emp {
        validateCreateRequest(request)

        val empNum: String = empNumGenerator.generateEmpNum(request.tenantId)

        val result = Emp(
            request.tenantId,
            EmpStatus.ofCode(request.statusCode),
            userId
        )

        result.empNum = empNum
        result.idNum = request.idNum
        result.dob = request.dob
        result.orgId = request.orgId
        result.name = request.name
        result.gender = Gender.ofCode(request.genderCode)


        request.skills.forEach { s ->
            result.addSkill(
                s.skillTypeId,
                SkillLevel.ofCode(s.levelCode),
                s.duration,
                userId
            )
        }

        request.experiences.forEach { e ->
            result.addExperience(
                Period.of(e.getStartDate(), e.getEndDate()),
                e.company,
                userId
            )
        }

        request.postCodes.forEach { p -> result.addEmpPost(p, userId) }

        return result
    }

    private fun validateCreateRequest(request: CreateEmpRequest) {
        assertOrg.shouldValid(request.tenantId, request.orgId)
    }

    fun toResponse(emp: Emp): EmpResponse {
        val thisSkills = emp.getSkills()
            .filter { s -> s.changingStatus != ChangingStatus.DELETED }
            .map { s ->
                SkillDto(
                    s.id,
                    s.skillTypeId,
                    s.level.code(),
                    s.duration
                )
            }

        val thisExperiences = emp.getExperiences().map { e ->
            WorkExperienceDto(
                e.period.start,
                e.period.end,
                e.company
            )
        }

        return EmpResponse(
            id = emp.id,
            tenantId = emp.tenantId,
            orgId = emp.orgId,
            empNum = emp.empNum,
            idNum = emp.idNum,
            name = emp.name,
            genderCode = emp.gender.code,
            dob = emp.dob,
            statusCode = emp.status.code,
            skills = thisSkills,
            experiences = thisExperiences,
            postCodes = emptyList(),
        )
    }
}
