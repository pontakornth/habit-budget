package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

class OfflineStreakRepository(private val streakDao: StreakDao): StreakRepository {
    override fun getAll(): Flow<List<Streak>> {
        return streakDao.getAllStreaks()
    }

    override suspend fun insertStreak(streak: Streak) {
        TODO("Not yet implemented")
    }
}