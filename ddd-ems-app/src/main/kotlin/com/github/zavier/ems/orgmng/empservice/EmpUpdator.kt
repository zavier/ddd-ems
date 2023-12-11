package com.github.zavier.ems.orgmng.empservice

import com.github.zavier.ems.orgmng.emp.*
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class EmpUpdator {
    fun update(emp: Emp, request: UpdateEmpRequest, userId: Long) {
        emp.idNum = request.idNum
        emp.name = request.name
        emp.dob = request.dob
        emp.gender = Gender.ofCode(request.genderCode)
        emp.lastUpdatedBy = userId
        emp.lastUpdatedAt = LocalDateTime.now()
        emp.toUpdate()


        updateSkills(emp, request, userId)
        updateExperiences(emp, request, userId)
    }

    private fun updateSkills(emp: Emp, request: UpdateEmpRequest, userId: Long) {
        deleteAbsentSkills(emp, request)
        operatePresentSkills(emp, request, userId)
    }

    private fun updateExperiences(emp: Emp, request: UpdateEmpRequest, userId: Long) {
        deleteAbsentExperiences(emp, request)
        operatePresentExperiences(emp, request, userId)
    }

    private fun operatePresentExperiences(emp: Emp, request: UpdateEmpRequest, userId: Long) {
        for (experienceDto in request.experiences) {
            val experienceMaybe: WorkExperience? = emp.getExperience(
                experienceDto.period
            )

            if (experienceMaybe != null) {
                emp.updateExperience(
                    experienceDto.period,
                    experienceDto.company,
                    userId
                )
            } else {
                emp.addExperience(
                    experienceDto.period,
                    experienceDto.company,
                    userId
                )
            }
        }
    }

    private fun deleteAbsentExperiences(emp: Emp, request: UpdateEmpRequest) {
        emp.getExperiences().forEach {
            if (request.isExperienceAbsent(it)) {
                emp.deleteExperience(it.period)
            }
        }
    }

    private fun deleteAbsentSkills(emp: Emp, request: UpdateEmpRequest) {
        emp.getSkills().forEach {
            if (request.isSkillAbsent(it)) {
                emp.deleteSkill(it.skillTypeId)
            }
        }
    }

    private fun operatePresentSkills(emp: Emp, request: UpdateEmpRequest, userId: Long) {
        for (skill in request.skills) {
            val skillMaybe: Skill? = emp.getSkill(skill.skillTypeId)
            if (skillMaybe != null) {
                emp.updateSkill(
                    skill.skillTypeId,
                    SkillLevel.ofCode(skill.levelCode),
                    skill.duration,
                    userId
                )
            } else {
                emp.addSkill(
                    skill.skillTypeId,
                    SkillLevel.ofCode(skill.levelCode),
                    skill.duration,
                    userId
                )
            }
        }
    }
}
