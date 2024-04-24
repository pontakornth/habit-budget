package dev.pontakorn.habitbudget.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String

)
