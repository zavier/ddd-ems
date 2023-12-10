package com.github.zavier.ems.orgmng.emp


import com.github.zavier.ems.common.framework.BusinessException
import com.github.zavier.ems.common.framework.WithCode
import java.util.*

enum class Gender(val code: String, val desc: String) : WithCode {
    MALE("M", "男"),
    FEMALE("F", "女");

    override fun code(): String {
        return code
    }

    fun desc(): String {
        return desc
    }

    companion object {
        fun ofCode(code: String): Gender {
            return Arrays.stream(entries.toTypedArray())
                .filter { v: Gender -> v.code == code }
                .findAny()
                .orElseThrow<RuntimeException> { BusinessException("性别代码错误！") }
        }
    }
}
