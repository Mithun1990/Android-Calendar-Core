package com.naim.android_calendar_core.config.week_config

import com.naim.android_calendar_core.model.WeekItem

interface IWeekConfig {
    fun getWeekItems(): List<WeekItem>
    fun getMapOfWeekIdVsDay(): Map<Int, Int>
    fun getMapOfWeekDayVsId(): Map<Int, Int>
}