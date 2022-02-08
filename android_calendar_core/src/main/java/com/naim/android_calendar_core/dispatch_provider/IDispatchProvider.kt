package com.naim.android_calendar_core.dispatch_provider

import com.naim.android_calendar_core.events.CalendarEvent

interface IDispatchProvider {
    fun onEvent(event: CalendarEvent)
}