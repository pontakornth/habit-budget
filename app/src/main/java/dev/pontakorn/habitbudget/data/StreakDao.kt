package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StreakDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStreak(streak: Streak)

    @Query("SELECT * FROM streak")
    fun getAllStreaks(): Flow<List<Streak>>

    @Query("SELECT COUNT(1) FROM streak WHERE streak_date = :date")
    fun hasStreak(date: Long): Flow<Int>

}