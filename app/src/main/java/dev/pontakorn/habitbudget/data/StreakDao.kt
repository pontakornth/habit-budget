package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Transaction as DatabaseTransaction

@Dao
interface StreakDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStreak(streak: Streak)

    @Query("SELECT * FROM streak")
    fun getAllStreaks(): Flow<List<Streak>>

    @DatabaseTransaction
    @Query("SELECT COUNT(1) FROM streak WHERE streak_date = :date")
    suspend fun hasStreak(date: Long): Int

}