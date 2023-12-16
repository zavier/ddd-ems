package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.AggregateRoot
import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.common.valueobject.Period
import java.time.LocalDate
import java.time.LocalDateTime

class Emp : AggregateRoot {
    var id: Long = 0
    val tenantId: Long
    var orgId: Long = 0
    var empNum: String = ""
    var idNum: String = ""
    var name: String = ""
    var gender: Gender = Gender.MALE
    var dob: LocalDate = LocalDate.now()
    var status: EmpStatus = EmpStatus.PROBATION

    private val skills: MutableMap<Long, Skill> = mutableMapOf()

    private val experiences: MutableMap<Period, WorkExperience> = mutableMapOf()

    private val empPosts: MutableList<EmpPost> = mutableListOf()

    // 用于新建员工
    constructor(tenantId: Long, status: EmpStatus, createdBy: Long) : super(LocalDateTime.now(), createdBy) {
        this.tenantId = tenantId
        this.status = status
    }

    //用于从数据库重建员工
    constructor(tenantId: Long, id: Long, createdAt: LocalDateTime, createdBy: Long) : super(createdAt, createdBy) {
        this.id = id
        this.tenantId = tenantId
    }

    fun becomeRegular(): Emp {
        status = status.becomeRegular()
        return this
    }

    fun terminate(): Emp {
        status = status.terminate()
        return this
    }

    fun getSkills(): List<Skill> {
        return skills.values.toList()
    }

    fun getSkill(skillTypeId: Long): Skill? {
        return skills[skillTypeId]
    }

    fun addSkill(skillTypeId: Long, level: SkillLevel, duration: Int, userId: Long) {
        skillTypeShouldNotDuplicated(skillTypeId)

        val newSkill = Skill(tenantId, skillTypeId, level, duration, userId)

        skills[skillTypeId] = newSkill
    }

    fun updateSkill(skillTypeId: Long, level: SkillLevel, duration: Int, userId: Long): Emp {
        val theSkill: Skill? = getSkill(skillTypeId)
        requireNotNull(theSkill) { "不存在要修改的skillTypeId！" }

        if (theSkill.level != level || theSkill.duration != duration) {
            theSkill.setLevel(level)
                .setDuration(duration)
                .setLastUpdatedBy(userId)
                .setLastUpdatedAt(LocalDateTime.now()).toUpdate()
        }
        return this
    }

    fun deleteSkill(skillTypeId: Long): Emp {
        val theSkill: Skill? = getSkill(skillTypeId)
        requireNotNull(theSkill) { "Skills中不存在要删除的skillTypeId！" }

        theSkill.toDelete()
        return this
    }


    private fun skillTypeShouldNotDuplicated(newSkillTypeId: Long) {
        if (skills[newSkillTypeId] != null) {
            throw BusinessException("同一技能不能录入两次！")
        }
    }

    fun getExperiences(): List<WorkExperience> {
        return experiences.values.toList()
    }

    fun getExperience(period: Period): WorkExperience? {
        return experiences[period]
    }

    fun addExperience(period: Period, company: String, userId: Long) {
        durationShouldNotOverlap(period)

        val newExperience = WorkExperience(
            tenantId,
            period,
            company,
            LocalDateTime.now(),
            userId,
        )
        experiences[period] = newExperience
    }

    fun updateExperience(period: Period, company: String, userId: Long): Emp {
        val theExperience: WorkExperience? = getExperience(period)
        requireNotNull(theExperience) { "Experiences中不存在要修改的period！" }

        if (theExperience.company != company) {
            theExperience.setCompany(company)
                .setLastUpdatedBy(userId)
                .setLastUpdatedAt(LocalDateTime.now())
                .toUpdate()
        }

        return this
    }

    fun deleteExperience(period: Period): Emp {
        val theExperience: WorkExperience? = getExperience(period)
        requireNotNull(theExperience) { "Experiences中不存在要删除的period！" }
        theExperience.toDelete()
        return this
    }

    private fun durationShouldNotOverlap(newPeriod: Period) {
        if (experiences.values.any { it.period.overlap(newPeriod) }) {
            throw BusinessException("工作经验的时间段不能重叠!")
        }
    }


    fun getPosts(): List<EmpPost> {
        return empPosts.toList()
    }

    fun addEmpPost(postCode: String, userId: Long): Emp {
        val post = EmpPost(LocalDateTime.now(), userId)
        post.postCode = postCode
        empPosts.add(post)
        return this
    }

    fun deleteEmpPost(postCode: String, userId: Long): Emp {
        val post = empPosts.firstOrNull { it.postCode == postCode }
        requireNotNull(post) { "不存在要删除的岗位！" }
        post.toDelete()
        return this
    }
}
