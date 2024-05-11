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

    @DatabaseTransaction
    @Query("SELECT COUNT(1) FROM streak WHERE streak_date = :date")
    fun hasStreakFlow(date: Long): Flow<Int>

    @DatabaseTransaction
    @Query(
        """WITH last_missing_day as (
            WITH day_with_diff as (
                WITH day_with_prev as (SELECT streak_date day_selected,
                    (SELECT MAX(streak_date) from streak s2 WHERE s2.streak_date < s1.streak_date) day_prev
                            from streak s1)
                        SELECT day_selected, day_prev, julianday(day_selected / 1000, 'unixepoch') - julianday(day_prev / 1000, 'unixepoch') as diff FROM day_with_prev
                        ORDER BY day_selected DESC
            )
                    SELECT MAX(day_selected) as last_day, diff FROM day_with_diff WHERE diff > 1
        )
                SELECT julianday(max(streak_date) / 1000, 'unixepoch') - julianday(coalesce((select last_day from last_missing_day), min(streak_date)) / 1000, 'unixepoch') + 1 from streak
        """
    )
    fun getStreakLength(): Flow<Int>

}