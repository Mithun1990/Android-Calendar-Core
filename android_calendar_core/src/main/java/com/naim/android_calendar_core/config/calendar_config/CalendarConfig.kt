package com.naim.android_calendar_core.config.calendar_config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.naim.android_calendar_core.config.month_config.MonthConfigImpl
import com.naim.android_calendar_core.config.week_config.IWeekConfig
import com.naim.android_calendar_core.config.week_config.IWeekConfigImpl
import com.naim.android_calendar_core.config.year_config.IYearConfig
import com.naim.android_calendar_core.config.year_config.YearConfigImpl
import com.naim.android_calendar_core.util.Constants
import java.util.*

class CalendarConfig {
    val weekConfig: IWeekConfig by lazy { IWeekConfigImpl() }
    val monthConfig: MonthConfigImpl by lazy { MonthConfigImpl(weekConfig) }
    val yearConfig: IYearConfig by lazy { YearConfigImpl(weekConfig) }
    val PREFER_DATE_FORMAT = Constants.PREFER_DATE_FORMAT
    val MONTH_NAME_DATE_FORMAT = Constants.MONTH_NAME_DATE_FORMAT
    val CALENDAR_MONTH_TITLE_DATE_FORMAT = Constants.CALENDAR_MONTH_TITLE_DATE_FORMAT
    var date: Date = Date()
    val currentDate: Date = date
    var minDate: Date = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1900)
        set(Calendar.MONTH, 0)
        set(Calendar.DAY_OF_MONTH, 1)
    }.time
    var maxDate: Date = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2100)
        set(Calendar.MONTH, 11)
        set(Calendar.DAY_OF_MONTH, 31)
    }.time
    var selectedDateTextColor = Color.White
    var currentDateTextColor = Color.White
    var selectedDateBgColor = Color.Magenta
    var currentDateBgColor = Color.Blue
    var normalDateTextColor = Color.Black
    var disableDateTextColor = Color.Yellow
    var holidayTextColor = Color.Red
    var monthTitleTextColor = Color.LightGray
    var monthTitleTextStyle: TextStyle? = null
    var isMonthChangeEnabled = true
    var isYearChangedEnabled = true
    fun getFormattedDate(year: Int, month: Int, day: Int): Date {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }.time
    }

    var monthListBgColor = Color.Cyan
    var monthListItemTextColor = Color.LightGray
    var monthListItemTextStyle: TextStyle? = null
}