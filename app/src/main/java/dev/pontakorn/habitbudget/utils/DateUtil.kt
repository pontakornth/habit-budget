package dev.pontakorn.habitbudget.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.util.Calendar
import java.util.Date

object DateUtil {
    private val dateFormatter = SimpleDateFormat("dd MMMM yyyy")
    private val timeFormatter = SimpleDateFormat("HH:mm")
    fun getMonthDuration(month: Int, year: Int): Pair<Date, Date> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        val currentMonth = calendar.time
        // Yes, Calendar can handle this.
        calendar.set(year, month + 1, 1)
        val nextMonth = calendar.time
        return currentMonth to nextMonth
    }

    fun getFormattedDate(date: Date): String {
        return dateFormatter.format(date)
    }

    fun getFormattedTime(time: Int): String {
        return timeFormatter.format(time)
    }

    fun getFormattedTime(hour: Int, minute: Int): String {
        // Timezone is irrelevant here. It is for formatting only.
        timeFormatter.timeZone = TimeZone.getTimeZone("UTC")
        return timeFormatter.format((hour * 3600 + minute * 60) * 1000)
    }
}