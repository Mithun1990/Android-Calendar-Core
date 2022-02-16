package com.naim.android_calendar_core.config.month_config

import com.naim.android_calendar_core.model.ComposeCalendar
import com.naim.android_calendar_core.model.MonthItem
import java.util.*

interface IMonthConfig{
    fun getMonthItems(
        date: Date,
        holidayList: List<Int>,
        listOfDisableDate: List<Date> = emptyList()
    ): ComposeCalendar
}