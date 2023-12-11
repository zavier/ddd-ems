package com.github.zavier.ems.orgmng.empservice

import com.github.zavier.ems.common.valueobject.Period
import java.time.LocalDate

class WorkExperienceDto(startDate: LocalDate, endDate: LocalDate, company: String) {
    val id: Long? = null
    private var startDate: LocalDate
    private var endDate: LocalDate
    var company: String

    init {
        this.startDate = startDate
        this.endDate = endDate
        this.company = company
    }

    fun getStartDate(): LocalDate {
        return startDate
    }

    fun setStartDate(startDate: LocalDate) {
        this.startDate = startDate
    }

    fun getEndDate(): LocalDate {
        return endDate
    }

    fun setEndDate(endDate: LocalDate) {
        this.endDate = endDate
    }

    val period: Period
        get() = Period.of(getStartDate(), getEndDate())
}
