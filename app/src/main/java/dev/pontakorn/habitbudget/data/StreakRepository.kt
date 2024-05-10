package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

interface StreakRepository {
    fun getAll(): Flow<List<Streak>>
    suspend fun insertStreak(streak: Streak)
}