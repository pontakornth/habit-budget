package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class TransactionDisplayItem(
    val transactionTitle: String,
    val transactionAmount: Double,
    val transactionIcon: ImageVector,
    val transactionSourceWalletIcon: ImageVector,
    val transactionDestinationWalletIcon: ImageVector?,
    val transactionDate: Date
)
