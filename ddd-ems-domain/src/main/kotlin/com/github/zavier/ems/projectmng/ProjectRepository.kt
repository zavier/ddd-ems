package com.github.zavier.ems.projectmng

interface ProjectRepository {

    fun findAssignmentsByEmpId(empId: Long): Collection<Project>

    fun findCommonProjects(): Collection<Project>
}