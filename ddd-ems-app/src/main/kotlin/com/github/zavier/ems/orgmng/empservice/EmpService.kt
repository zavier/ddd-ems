package com.github.zavier.ems.orgmng.empservice

import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.orgmng.emp.Emp
import com.github.zavier.ems.orgmng.emp.EmpRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmpService(
    private val empRepository: EmpRepository,
    private val assembler: EmpAssembler,
    private val updator: EmpUpdator
) {

    @Transactional
    fun addEmp(request: CreateEmpRequest, userId: Long): EmpResponse {
        val emp = assembler.toEmp(request, userId)
        empRepository.save(emp)
        return assembler.toResponse(emp)
    }

    @Transactional
    fun updateEmp(empId: Long, request: UpdateEmpRequest, userId: Long): EmpResponse {
        val emp: Emp = empRepository.findById(request.tenantId, empId)
            ?: throw BusinessException("Emp id($empId) 不正确！")

        updator.update(emp, request, userId)

        if (!empRepository.save(emp)) {
            throw BusinessException("这个员工已被其他人同时修改了，请重新修改！")
        }
        return assembler.toResponse(emp)
    }
}
