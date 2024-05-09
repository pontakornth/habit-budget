package dev.pontakorn.habitbudget.ui.transaction

import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import java.util.Date

data class TransactionViewState(
    val category: Category? = null,
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val sourceWallet: Wallet? = null,
    val destinationWallet: Wallet? = null,
    // Satang
    val amount: Double = 0.0,
    val transactionDate: Date = Date(),
    // Hour to Minute
    val transactionTime: Pair<Int, Int> = 0 to 0
)
