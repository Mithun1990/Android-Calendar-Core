package com.naim.android_calendar_core.model

import java.util.*

data class Year(
    var year: Int,
    var isLeapYear: Boolean,
    var listOfMonth: List<Month>,
    var selectedDate: Date
)
