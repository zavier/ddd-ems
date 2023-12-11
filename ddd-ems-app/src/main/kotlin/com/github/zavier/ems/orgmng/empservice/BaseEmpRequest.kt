package com.github.zavier.ems.orgmng.empservice

import java.time.LocalDate

abstract class BaseEmpRequest {
    var tenantId: Long = 0
    var idNum: String = ""
    var name: String = ""
    var genderCode: String = ""
    var dob: LocalDate = LocalDate.now()

    var skills: MutableList<SkillDto> = mutableListOf()
    var experiences: MutableList<WorkExperienceDto> = mutableListOf()
    var postCodes: MutableList<String> = mutableListOf()
    open fun addSkill(skillTypeId: Long, levelCode: String, duration: Int): BaseEmpRequest {
        skills.add(SkillDto(skillTypeId, levelCode, duration))
        return this
    }

    open fun addSkill(id: Long, skillTypeId: Long, levelCode: String, duration: Int): BaseEmpRequest {
        skills.add(SkillDto(id, skillTypeId, levelCode, duration))
        return this
    }

    open fun addExperience(startDate: LocalDate, endDate: LocalDate, company: String): BaseEmpRequest {
        experiences.add(WorkExperienceDto(startDate, endDate, company))
        return this
    }
}
