package com.naim.android_calendar_core.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naim.android_calendar_core.config.calendar_config.CalendarConfig
import com.naim.android_calendar_core.viewmodel.CalendarViewModel

class CalendarViewModelFactory(private val calendarConfig: CalendarConfig = CalendarConfig()) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            return CalendarViewModel(calendarConfig) as T
        }
        throw IllegalArgumentException("Unknown class cast exception")
    }
}