package dev.pontakorn.habitbudget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,

    // I will use hardcoded icon for this.
    // It is not suitable for a real project outside of the classroom.
    // But, I think it is OK for this project.
    @ColumnInfo(name = "icon_name") val iconName: String

)
