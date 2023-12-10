package com.github.zavier.ems.tenantmng

enum class TenantStatus(val code: String, val desc: String) {
    EFFECTIVE("EF", "有效"),
    TERMINATED("TERMINATED", "终止");

    fun code(): String {
        return code
    }
}
