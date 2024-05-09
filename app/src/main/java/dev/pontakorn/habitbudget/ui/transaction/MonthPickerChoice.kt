package dev.pontakorn.habitbudget.ui.transaction

import android.icu.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

data class MonthPickerChoice(
    // 1 based
    val month: Int,
    val year: Int,
) {
    val monthFormatter = SimpleDateFormat("MMM yyyy")
    override fun toString(): String {
        val calender = Calendar.getInstance()
        calender.set(Calendar.MONTH, month - 1)
        calender.set(Calendar.YEAR, year)
        calender.set(Calendar.DAY_OF_MONTH, 1)
        calender.set(Calendar.HOUR_OF_DAY, 0)
        return monthFormatter.format(calender.time)
    }


    companion object {
        fun getMonthsRange(from: Date, to: Date = Date()): List<MonthPickerChoice> {
            val monthRange = mutableListOf<MonthPickerChoice>()
            val calendar = Calendar.getInstance()
            calendar.time = from
            do {
                monthRange.add(
                    MonthPickerChoice(
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR)
                    )
                )
                calendar.add(Calendar.MONTH, 1)
            } while (calendar.time.before(to))
            return monthRange
        }
    }

}
