package com.naim.android_calendar_core.model

import java.util.*

data class Month(
    val monthId: Int, val monthTitle: String,
    val noOfDays: Int, val yearOfMonth: Int, val previousMonthDays: Int, val firstDayOfMonth: Int,
    val selectedDate: Date, val listOfHolidays: List<Int>
)