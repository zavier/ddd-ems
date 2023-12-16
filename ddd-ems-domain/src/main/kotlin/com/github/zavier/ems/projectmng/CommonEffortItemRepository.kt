package com.github.zavier.ems.projectmng

interface CommonEffortItemRepository {

    fun findAll(): Collection<CommonEffortItem>
}