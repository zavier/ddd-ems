package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.AuditableEntity
import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.common.valueobject.Period
import java.time.LocalDateTime
import java.util.*

class Skill(val tenantId: Long, val skillTypeId: Long, createdBy: Long) :
    AuditableEntity(LocalDateTime.now(), createdBy) {

    var id: Long = 0
    var level: SkillLevel = SkillLevel.BEGINNER
    var duration: Int = 0

    fun setLevel(level: SkillLevel): Skill {
        this.level = level
        return this
    }

    fun setDuration(duration: Int): Skill {
        this.duration = duration
        return this
    }
}

enum class SkillLevel(val code: String, val desc: String) {
    BEGINNER("BEG", "初级"),
    MEDIUM("MED", "中级"),
    ADVANCED("ADV", "高级");

    fun code(): String {
        return code
    }

    companion object {
        fun ofCode(code: String): SkillLevel {
            return Arrays.stream(entries.toTypedArray())
                .filter { v: SkillLevel -> v.code == code }
                .findAny()
                .orElseThrow<RuntimeException> {
                    BusinessException(
                        "技能水平代码错误！"
                    )
                }
        }
    }
}

class WorkExperience(
    val id: Long? = null,
    val tenantId: Long,
    val period: Period,
    createdAt: LocalDateTime,
    createdBy: Long
) : AuditableEntity(createdAt, createdBy) {
    var company: String = ""

    fun getWorkPeriod(): Period {
        return this.period
    }

    fun setCompany(company: String): WorkExperience {
        this.company = company
        return this
    }
}


