package dev.pontakorn.habitbudget.data

import dev.pontakorn.habitbudget.utils.DateUtil.getPureDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
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

    override suspend fun streaksExistYesterday(): Boolean {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1)
        val yesterday = calendar.time
        val midnight = getPureDate(yesterday)
        return streakDao.hasStreak(midnight.time) > 0
    }

    override suspend fun insertStreak(streak: Streak) {
        // Current
        streakDao.insertStreak(streak)

    }

    override fun streaksExistTodayFlow(): Flow<Boolean> {
        val midnight = getPureDate(Date())
        return streakDao.hasStreakFlow(midnight.time).map { it > 0 }
    }

    override fun streaksExistYesterdayFlow(): Flow<Boolean> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1)
        val yesterday = calendar.time
        val midnight = getPureDate(yesterday)
        return streakDao.hasStreakFlow(midnight.time).map { it > 0 }
    }

    override fun getStreakLength(): Flow<Int> {
        return streakDao.getStreakLength()
    }
}