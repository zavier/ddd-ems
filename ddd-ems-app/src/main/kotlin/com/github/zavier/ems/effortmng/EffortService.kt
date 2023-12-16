package com.github.zavier.ems.effortmng

import com.github.zavier.ems.projectmng.CommonEffortItemRepository
import com.github.zavier.ems.projectmng.ProjectRepository
import org.springframework.stereotype.Service

@Service
class EffortService(
    private val projectRepository: ProjectRepository,
    private val commonEffortItemRepository: CommonEffortItemRepository) {

    fun findAvailableEffortItems(empId: Long): AvailableEffortItems {
        val assignments = projectRepository.findAssignmentsByEmpId(empId)
        val commonProjects = projectRepository.findCommonProjects()
        val commonEffortItems = commonEffortItemRepository.findAll()

        val result = AvailableEffortItems()

        addResult(ItemType.ASSIGNED_PROJECT, assignments, result)
        addResult(ItemType.COMMON_PROJECT, commonProjects, result)
        addResult(ItemType.COMMON, commonEffortItems, result)

        return result
    }

    fun addResult(itemType: ItemType, items: Collection<EffortItem>, result: AvailableEffortItems) {
        items.forEach { result.addItem(itemType, it.getEffortItemId(), it.getName()) }
    }
}