package com.naim.android_calendar_core.config.year_config

import com.naim.android_calendar_core.config.month_config.MonthConfigImpl
import com.naim.android_calendar_core.config.week_config.IWeekConfig
import java.util.*

class YearConfigImpl(weekConfig:IWeekConfig):MonthConfigImpl(weekConfig), IYearConfig {
    override fun getYearList(date: Date) {
        TODO("Not yet implemented")
    }
}