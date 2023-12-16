package com.github.zavier.ems.projectmng

import com.github.zavier.ems.common.framework.AggregateRoot
import com.github.zavier.ems.common.valueobject.Period
import com.github.zavier.ems.effortmng.EffortItem
import java.time.LocalDateTime

class Project(
    val tenantId: Long,
    val effortItemId: Long,
    val name: String,
    val period: Period,
    val status: Status,
    val clientProject: Boolean, // 是否客户项目
    val shouldAssignMember: Boolean, // 是否要分配人员
    val effoRtGranulArIty: EffortGranularity, // 工时粒度
    createdAt: LocalDateTime,
    createdBy: Long
) : AggregateRoot(createdAt, createdBy), EffortItem {

    // 项目经理
    val mngs = mutableMapOf<Period, ProjectMng>()

    // 子项目
    val subProjects = mutableListOf<SubProject>()


    override fun getEffortItemId(): Long {
        return effortItemId;
    }

    override fun getName(): String {
        return name
    }

}

class EffortGranularity

class ProjectMng

enum class Status {

}