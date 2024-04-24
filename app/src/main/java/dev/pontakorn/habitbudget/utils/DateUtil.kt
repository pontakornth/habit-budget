package dev.pontakorn.habitbudget.utils

import java.util.Calendar
import java.util.Date

object DateUtil {
    fun getMonthDuration(month: Int, year: Int): Pair<Date, Date> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        val currentMonth = calendar.time
        // Yes, Calendar can handle this.
        calendar.set(year, month + 1, 1)
        val nextMonth = calendar.time
        return currentMonth to nextMonth
    }
}