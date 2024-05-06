package dev.pontakorn.habitbudget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CategoryType {
    INCOME,
    EXPENSE
}

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "category_type") val categoryType: CategoryType,
    @ColumnInfo(name = "icon_name", defaultValue = "Shopping") val iconName: String = "Shopping"
)
