package com.naim.android_calendar_core.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.naim.android_calendar_core.config.calendar_config.CalendarConfig
import com.naim.android_calendar_core.extensions.getCalendarMonthTitle

class CalendarUiState(calendarConfig: CalendarConfig) {
    var selectedDate by mutableStateOf(calendarConfig.date)
    var selectedMonth by mutableStateOf(calendarConfig.date.getCalendarMonthTitle(calendarConfig.CALENDAR_MONTH_TITLE_DATE_FORMAT))
    var currentDate by mutableStateOf(calendarConfig.currentDate)
    var currentCalendarUiView by mutableStateOf(CalendarUiView.DAY_VIEW)
}

enum class CalendarUiView {
    DAY_VIEW,
    MONTH_VIEW
}