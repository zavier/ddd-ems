package com.github.zavier.ems.orgmng.empservice

import java.time.LocalDate

data class EmpResponse(
    var id: Long,
    var tenantId: Long,
    var orgId: Long,
    var empNum: String,
    var idNum: String,
    var name: String,
    var genderCode: String,
    var dob: LocalDate,
    var statusCode: String,
    var skills: List<SkillDto>,
    var experiences: List<WorkExperienceDto>,
    var postCodes: List<String>
)
