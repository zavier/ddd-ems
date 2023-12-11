package com.github.zavier.ems.orgmng.empservice

import com.github.zavier.ems.common.valueobject.Period
import com.github.zavier.ems.orgmng.emp.Skill
import com.github.zavier.ems.orgmng.emp.WorkExperience
import java.time.LocalDate


class UpdateEmpRequest : BaseEmpRequest() {
    var empNum: String = ""

    override fun addSkill(skillTypeId: Long, levelCode: String, duration: Int): UpdateEmpRequest {
        super.addSkill(skillTypeId, levelCode, duration)
        return this
    }

    override fun addSkill(id: Long, skillTypeId: Long, levelCode: String, duration: Int): UpdateEmpRequest {
        super.addSkill(id, skillTypeId, levelCode, duration)
        return this
    }

    fun isSkillAbsent(otherSkill: Skill): Boolean {
        return skills.none { skill: SkillDto -> skill.skillTypeId == otherSkill.skillTypeId }
    }

    fun isExperienceAbsent(otherExperience: WorkExperience): Boolean {
        return experiences.none { experience: WorkExperienceDto ->
                Period.of(experience.getStartDate(), experience.getEndDate()) == otherExperience.period
            }
    }

    fun removeSkill(skillTypeId: Long): UpdateEmpRequest {
        skills.removeIf { skill: SkillDto -> skill.skillTypeId == skillTypeId }
        return this
    }

    fun updateSkill(typeId: Long, levelCode: String?, duration: Int): UpdateEmpRequest {
        skills.stream()
            .filter { skill: SkillDto -> skill.skillTypeId == typeId }
            .findFirst()
            .ifPresent { skill: SkillDto ->
                skill.levelCode = levelCode!!
                skill.duration = duration
            }
        return this
    }

    fun setExperiences(experiences: MutableList<WorkExperienceDto>): UpdateEmpRequest {
        super.experiences = experiences
        return this
    }

    override fun addExperience(startDate: LocalDate, endDate: LocalDate, company: String): UpdateEmpRequest {
        super.addExperience(startDate, endDate, company)
        return this
    }

    fun removeExperience(startDate: LocalDate, endDate: LocalDate): UpdateEmpRequest {
        experiences.removeIf { experience: WorkExperienceDto -> experience.getStartDate() == startDate && experience.getEndDate() == endDate }
        return this
    }

    fun updateExperience(startDate: LocalDate, endDate: LocalDate, company: String): UpdateEmpRequest {
        experiences.stream()
            .filter { experience: WorkExperienceDto -> experience.getStartDate() == startDate && experience.getEndDate() == endDate }
            .findFirst()
            .ifPresent { experience: WorkExperienceDto -> experience.company = company }
        return this
    }
}
