package com.github.zavier.ems.orgmng.empservice

class SkillDto {
    var id: Long = 0
    var skillTypeId: Long
    var levelCode: String // “BEG” - Beginner, "MED" - Medium, "ADV" - Advanced
    var duration: Int

    constructor(skillTypeId: Long, levelCode: String, duration: Int) {
        this.skillTypeId = skillTypeId
        this.levelCode = levelCode
        this.duration = duration
    }

    constructor(id: Long, skillTypeId: Long, levelCode: String, duration: Int) {
        this.id = id
        this.skillTypeId = skillTypeId
        this.levelCode = levelCode
        this.duration = duration
    }
}
