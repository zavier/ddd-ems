package com.github.zavier.ems.orgmng.empservice

class CreateEmpRequest(var orgId: Long) : BaseEmpRequest() {
    var statusCode: String = ""

}
