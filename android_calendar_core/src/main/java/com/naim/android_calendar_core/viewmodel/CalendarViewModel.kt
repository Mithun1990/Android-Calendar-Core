package com.naim.android_calendar_core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naim.android_calendar_core.config.calendar_config.CalendarConfig
import com.naim.android_calendar_core.extensions.*
import com.naim.android_calendar_core.model.MonthItem
import com.naim.android_calendar_core.state.CalendarUiState
import com.naim.android_calendar_core.state.CalendarUiView
import java.util.*

class CalendarViewModel(private val calendarConfig: CalendarConfig) : ViewModel() {
    private val _uiState: MutableLiveData<CalendarUiState> =
        MutableLiveData(CalendarUiState(calendarConfig))
    private val _monthItems: MutableLiveData<List<MonthItem>> = MutableLiveData(
        calendarConfig.monthConfig.getMonthItems(
            Date(), emptyList()
        )
    )
    private val _monthList =
        MutableLiveData(
            calendarConfig.monthConfig.getMonthList(
                uiState.value?.selectedDate?.getTheYear()
                    ?: calendarConfig.currentDate.getTheYear()
            )
        )
    val uiState: LiveData<CalendarUiState>
        get() = _uiState
    val monthItems: LiveData<List<MonthItem>>
        get() = _monthItems

    val monthList = _monthList

    fun nextMonth() {
        val nextDate = calendarConfig.getFormattedDate(
            calendarConfig.monthConfig.getNextYear(
                uiState.value!!.selectedDate.getTheMonth(),
                uiState.value!!.selectedDate.getTheYear()
            ),
            calendarConfig.monthConfig.getNextMonth(uiState.value!!.selectedDate.getTheMonth()),
            1
        )
        if (calendarConfig.maxDate < nextDate)
            return
        _monthItems.value = calendarConfig.monthConfig.getMonthItems(nextDate, emptyList())
        selectedMonthTitle(nextDate)
    }

    fun gotoNextYear() {
        val nextYear = calendarConfig.getFormattedDate(
            uiState.value!!.selectedDate.getTheYear() + 1,
            uiState.value!!.selectedDate.getTheMonth(),
            uiState.value!!.selectedDate.getTheDay()
        )
        if (calendarConfig.maxDate < nextYear)
            return
        _monthItems.value = calendarConfig.monthConfig.getMonthItems(nextYear, emptyList())
        selectedMonthTitle(nextYear)
    }

    fun gotoPreviousYear() {
        val nextYear = calendarConfig.getFormattedDate(
            uiState.value!!.selectedDate.getTheYear() - 1,
            uiState.value!!.selectedDate.getTheMonth(),
            uiState.value!!.selectedDate.getTheDay()
        )
        if (calendarConfig.minDate > nextYear)
            return
        _monthItems.value = calendarConfig.monthConfig.getMonthItems(nextYear, emptyList())
        selectedMonthTitle(nextYear)
    }

    fun gotoPreviousMonth() {
        val prevDate = calendarConfig.getFormattedDate(
            calendarConfig.monthConfig.getPrevMonthYear(
                uiState.value!!.selectedDate.getTheMonth(),
                uiState.value!!.selectedDate.getTheYear()
            ),
            calendarConfig.monthConfig.getPrevMonth(uiState.value!!.selectedDate.getTheMonth()),
            1
        )
        if (calendarConfig.minDate > prevDate)
            return
        _monthItems.value = calendarConfig.monthConfig.getMonthItems(prevDate, emptyList())
        selectedMonthTitle(prevDate)
    }

    fun selectedDate(value: Date) {
        println("Month $value")
        _uiState.value?.apply {
            this.selectedDate = value
            _uiState.value = this
        }
    }

    fun selectedMonthTitle(value: Date) {
        _uiState.value?.apply {
            this.selectedDate = value
            this.selectedMonth = value.formattedDate()
                .getCalendarMonthTitle(calendarConfig.CALENDAR_MONTH_TITLE_DATE_FORMAT)
            _uiState.value = this
        }
    }

    fun setCalendarUiView(value: CalendarUiView) {
        _uiState.value?.apply {
            this.currentCalendarUiView = value
            _uiState.value = this
        }
    }

    fun setMonth(value: Date) {
        _monthItems.value = calendarConfig.monthConfig.getMonthItems(
            value, emptyList()
        )
        selectedMonthTitle(value)
    }
}