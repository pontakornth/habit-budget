package dev.pontakorn.habitbudget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

enum class TransactionType {
    INCOME,
    EXPENSE,
    TRANSFER
}

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    // Transfer does not require category ID.
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    // This allow empty category and transfer
    @ColumnInfo(name = "transaction_type") val transactionType: TransactionType,
    // Amount represents the smallest unit in the currency (e.g. stang for Thai baht)
    // This makes aggregation (e.g. SUM) possible.
    val amount: Int,

    @ColumnInfo("transaction_time")
    val transactionTime: Date
)
