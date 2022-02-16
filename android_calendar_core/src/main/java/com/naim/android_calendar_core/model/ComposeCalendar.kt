package com.naim.android_calendar_core.model

data class ComposeCalendar(
    var year: Year,
    var month: Month,
    var selectedMonthItems: List<MonthItem>
)