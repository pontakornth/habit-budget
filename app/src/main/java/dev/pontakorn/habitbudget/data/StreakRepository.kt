package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface StreakRepository {
    fun getAll(): Flow<List<Streak>>
    suspend fun streaksExistToday(): Boolean
    suspend fun insertStreak(streak: Streak)

    suspend fun insertForToday() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        // 0 is for auto gen
        insertStreak(Streak(0, calendar.time))
    }

}