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

}