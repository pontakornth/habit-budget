package dev.pontakorn.habitbudget.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class TransactionType {
    INCOME,
    EXPENSE,
    TRANSFER
}

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    val id: Int,
    // Transfer does not require category ID.
    @ColumnInfo(name = "category_id") val categoryId: Int?,
    // This allow empty category and transfer
    @ColumnInfo(name = "transaction_type") val transactionType: TransactionType,
    // Amount represents the smallest unit in the currency (e.g. stang for Thai baht)
    // This makes aggregation (e.g. SUM) possible.

    // Source wallet ID is a must
    @ColumnInfo(name = "source_wallet_id") val sourceWalletId: Int,
    // Destination wallet ID is only when transfer
    @ColumnInfo(name = "destination_wallet_id") val destinationWalletId: Int?,
    val amount: Int,

    @ColumnInfo("transaction_time")
    val transactionTime: Date
)
