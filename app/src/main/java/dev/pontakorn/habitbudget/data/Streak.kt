package dev.pontakorn.habitbudget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Streak(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("streak_id")
    val streakId: Int,
    @ColumnInfo("streak_date")
    val streakDate: Date
)
