package com.github.zavier.ems.common.framework

import java.time.LocalDateTime


abstract class AuditableEntity(var createdAt: LocalDateTime, var createdBy: Long) {
    var changingStatus: ChangingStatus = ChangingStatus.NEW
    var lastUpdatedAt: LocalDateTime = LocalDateTime.now()
    var lastUpdatedBy: Long = 0

    fun toUpdate() {
        this.changingStatus = ChangingStatus.UPDATED
    }

    fun toDelete() {
        this.changingStatus = ChangingStatus.DELETED
    }

    fun toUnChange() {
        this.changingStatus = ChangingStatus.UNCHANGED
    }

    fun setLastUpdatedAt(lastUpdatedAt: LocalDateTime): AuditableEntity {
        this.lastUpdatedAt = lastUpdatedAt
        return this
    }

    fun setLastUpdatedBy(lastUpdatedBy: Long): AuditableEntity {
        this.lastUpdatedBy = lastUpdatedBy
        return this
    }
}


open class AggregateRoot(createdAt: LocalDateTime, createdBy: Long) : AuditableEntity(createdAt, createdBy) {
    // 乐观锁
    var version: Long = 0
}

interface WithCode {
    fun code(): String
}


enum class ChangingStatus {
    NEW,  // 新增的，数据库还没有，需要 insert
    UNCHANGED,  // 数据库中已经存在，没有变化，因此不需要任何操作
    UPDATED,  // 数据库中已经存在，发生了变化，需要 update
    DELETED // 数据库中已经存在，要删除，需要 delete
}

