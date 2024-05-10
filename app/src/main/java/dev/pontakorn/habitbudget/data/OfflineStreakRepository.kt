package dev.pontakorn.habitbudget.data

import dev.pontakorn.habitbudget.utils.DateUtil.getPureDate
import kotlinx.coroutines.flow.Flow
import java.util.Date

class OfflineStreakRepository(private val streakDao: StreakDao): StreakRepository {
    override fun getAll(): Flow<List<Streak>> {
        return streakDao.getAllStreaks()
    }

    override suspend fun streaksExistToday(): Boolean {
        // Note:
        val midnight = getPureDate(Date())
        return streakDao.hasStreak(midnight.time) > 0
    }

    override suspend fun insertStreak(streak: Streak) {
        // Current
        streakDao.insertStreak(streak)

    }
}