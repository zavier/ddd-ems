package com.github.zavier.ems.effortmng

class EffortItemDTO(val effortItemId: Long, val name: String) {
}


class AvailableEffortItems  {
    private val assignments = mutableListOf<EffortItemDTO>()
    private val subProjects = mutableListOf<EffortItemDTO>()
    private val commonProjects = mutableListOf<EffortItemDTO>()
    private val commonEffortItems = mutableListOf<EffortItemDTO>()

    fun addItem(type: ItemType, effortItemId: Long, name: String) {
        when (type) {
            ItemType.ASSIGNED_PROJECT -> {
                assignments.add(EffortItemDTO(effortItemId, name))
            }

            ItemType.SUB_PROJECT -> {
                subProjects.add(EffortItemDTO(effortItemId, name))
            }

            ItemType.COMMON_PROJECT -> {
                commonProjects.add(EffortItemDTO(effortItemId, name))
            }

            ItemType.COMMON -> {
                commonEffortItems.add(EffortItemDTO(effortItemId, name))
            }
        }
    }
}


enum class ItemType {
    ASSIGNED_PROJECT,
    COMMON_PROJECT,
    SUB_PROJECT,
    COMMON
}
