package com.naim.android_calendar_core.config.month_config

import com.naim.android_calendar_core.model.Month
import com.naim.android_calendar_core.util.Constants
import java.time.Year
import java.util.*

abstract class BaseMonthConfig {

    fun getLengthOfMonth(month: Int, year: Int): Int {
        return if (month == 1) 28 +
                (if (year % 4 == 0) 1 else 0) -
                (if (year % 100 == 0) if (year % 400 == 0) 0 else 1 else 0) else 31 - (month) % 7 % 2
    }

    fun isLeapYear(year: Int): Boolean {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)
    }

    fun getPrevMonthYear(month: Int, year: Int): Int {
        var year = year
        if (month == Constants.MONTH_START) {
            year -= 1
        }
        return year
    }

    fun getPrevMonth(month: Int): Int {
        var month = month
        month = if (month == Constants.MONTH_START) {
            Constants.MONTH_END
        } else {
            month - 1
        }
        return month
    }

    fun getNextMonth(month: Int): Int {
        return if (month == Constants.MONTH_END) {
            0
        } else {
            month + 1
        }
    }

    fun getNextYear(month: Int, year: Int): Int {
        var year = year
        if (month == Constants.MONTH_END) {
            year += 1
        }
        return year
    }

    abstract fun configureMonth(date: Date, holidayList: List<Int>): Month

    abstract fun getMonthList(year: Int): List<Month>
}