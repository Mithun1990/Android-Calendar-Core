package com.naim.android_calendar_core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naim.android_calendar_core.config.calendar_config.CalendarConfig
import com.naim.android_calendar_core.extensions.*
import com.naim.android_calendar_core.model.ComposeCalendar
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
        ).selectedMonthItems
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
        val composeCalendar = calendarConfig.monthConfig.getMonthItems(nextDate, emptyList())
        _monthItems.value =
            composeCalendar.selectedMonthItems
        recycleCalendar(composeCalendar)
    }

    fun gotoNextYear() {
        val nextYear = calendarConfig.getFormattedDate(
            uiState.value!!.selectedDate.getTheYear() + 1,
            uiState.value!!.selectedDate.getTheMonth(),
            uiState.value!!.selectedDate.getTheDay()
        )
        if (calendarConfig.maxDate < nextYear)
            return
        val composeCalendar = calendarConfig.monthConfig.getMonthItems(nextYear, emptyList())
        _monthItems.value =
            composeCalendar.selectedMonthItems
        recycleCalendar(calendarConfig.monthConfig.getMonthItems(nextYear, emptyList()))
    }

    fun gotoPreviousYear() {
        val nextYear = calendarConfig.getFormattedDate(
            uiState.value!!.selectedDate.getTheYear() - 1,
            uiState.value!!.selectedDate.getTheMonth(),
            uiState.value!!.selectedDate.getTheDay()
        )
        if (calendarConfig.minDate > nextYear)
            return
        val composeCalendar = calendarConfig.monthConfig.getMonthItems(nextYear, emptyList())
        _monthItems.value = composeCalendar.selectedMonthItems
        recycleCalendar(composeCalendar)
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
        val composeCalendar = calendarConfig.monthConfig.getMonthItems(prevDate, emptyList())
        _monthItems.value = composeCalendar.selectedMonthItems
        recycleCalendar(composeCalendar)
    }

    fun selectedDate(value: Date) {
        println("Month $value")
        _uiState.value?.apply {
            this.selectedDate = value
            _uiState.value = this
        }
    }

    fun recycleCalendar(value: ComposeCalendar) {
        _uiState.value?.apply {
            this.selectedDate = value.month.selectedDate
            this.selectedMonth = value.month.selectedDate.formattedDate()
                .getCalendarMonthTitle(calendarConfig.CALENDAR_MONTH_TITLE_DATE_FORMAT)
            this.currentMonth = value.month
            this.currentYear = value.year
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
        ).selectedMonthItems
        selectedDate(value)
        _uiState.value?.apply {
            this.selectedMonth = value.formattedDate()
                .getCalendarMonthTitle(calendarConfig.CALENDAR_MONTH_TITLE_DATE_FORMAT)
            _uiState.value = this
        }
    }

    fun getMonthList() {
        _monthList.value = calendarConfig.monthConfig.getMonthList(
            uiState.value?.selectedDate?.getTheYear()
                ?: calendarConfig.currentDate.getTheYear()
        )
    }

}