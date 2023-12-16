package com.github.zavier.ems.orgmng.emp

import com.github.zavier.ems.common.framework.AuditableEntity
import com.github.zavier.ems.common.framework.BusinessException
import java.time.LocalDateTime
import java.util.*

class Skill(val tenantId: Long,
            val skillTypeId: Long,
            var level: SkillLevel,
            var duration: Int,
            createdBy: Long) :
    AuditableEntity(LocalDateTime.now(), createdBy) {

    var id: Long = 0

    internal fun setLevel(level: SkillLevel): Skill {
        this.level = level
        return this
    }

    internal fun setDuration(duration: Int): Skill {
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


