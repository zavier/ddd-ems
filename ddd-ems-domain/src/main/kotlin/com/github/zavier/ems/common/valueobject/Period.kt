package com.github.zavier.ems.common.valueobject

import java.time.LocalDate
import java.util.*

class Period(val start: LocalDate, val end: LocalDate) {

    init {
        require(!(start.isAfter(end))) { "结束日期不能小于开始日期！" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val period = other as Period
        return start == period.start && end == period.end
    }

    override fun hashCode(): Int {
        return Objects.hash(start, end)
    }

    fun overlap(other: Period): Boolean {
        return other.start.isBefore(this.end) && other.end.isAfter(this.start)
    }

    fun merge(other: Period): Period {
        val newStart = if (start.isBefore(other.start)) this.start else other.start
        val newEnd = if (end.isAfter(other.end)) this.end else other.end
        return Period(newStart, newEnd)
    }

    override fun toString(): String {
        return "$start ~ $end"
    }

    companion object {
        fun of(start: LocalDate, end: LocalDate): Period {
            return Period(start, end)
        }

        fun of(startYear: Int, startMonth: Int, startDay: Int, endYear: Int, endMonth: Int, endDay: Int): Period {
            return Period(LocalDate.of(startYear, startMonth, startDay), LocalDate.of(endYear, endMonth, endDay))
        }
    }
}
