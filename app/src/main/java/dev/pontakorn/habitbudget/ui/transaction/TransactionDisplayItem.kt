package dev.pontakorn.habitbudget.ui.transaction

import dev.pontakorn.habitbudget.ui.icons.IconInfo
import java.util.Date

data class TransactionDisplayItem(
    val id: Int = 0,
    val transactionTitle: String,
    val transactionAmount: Double,
    val transactionIcon: IconInfo,
    val transactionSourceWalletIcon: IconInfo,
    val transactionDestinationWalletIcon: IconInfo? = null,
    val transactionDate: Date
)
