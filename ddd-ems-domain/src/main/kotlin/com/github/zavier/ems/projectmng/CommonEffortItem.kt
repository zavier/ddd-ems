package com.github.zavier.ems.projectmng

import com.github.zavier.ems.common.framework.AggregateRoot
import com.github.zavier.ems.effortmng.EffortItem
import java.time.LocalDateTime

class CommonEffortItem(
    val tenantId: Long,
    val effortItemId: Long,
    val name: String, createdAt: LocalDateTime,
    createdBy: Long
) : AggregateRoot(createdAt, createdBy), EffortItem {
    override fun getEffortItemId(): Long {
        return effortItemId
    }

    override fun getName(): String {
        return name
    }
}