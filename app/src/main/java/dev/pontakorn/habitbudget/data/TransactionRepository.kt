package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAll(): Flow<List<Transaction>>
    fun getAll(transactionType: TransactionType): Flow<List<Transaction>>
    fun getWithMonthAndYear(month: Int, year: Int): Flow<List<Transaction>>
    fun getWithMonthAndYear(
        month: Int,
        year: Int,
        transactionType: TransactionType
    ): Flow<List<Transaction>>

    suspend fun insertTransaction(transaction: Transaction)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}