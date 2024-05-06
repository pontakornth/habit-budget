package dev.pontakorn.habitbudget.data

/**
 * Summary of transaction. Unit of money is in the smallest unit (e.g. satang)
 */
data class TransactionSummary(
    val income: Int,
    val expense: Int,
)
