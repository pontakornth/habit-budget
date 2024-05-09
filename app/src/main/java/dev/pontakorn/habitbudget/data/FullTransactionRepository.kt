package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FullTransactionRepository {
    fun getAll(): Flow<List<FullTransaction>>
    fun getAll(transactionType: TransactionType): Flow<List<FullTransaction>>
    fun getWithMonthAndYear(month: Int, year: Int): Flow<List<FullTransaction>>
    fun getWithMonthAndYear(
        month: Int,
        year: Int,
        transactionType: TransactionType
    ): Flow<List<FullTransaction>>
    fun getById(transactionId: Int): Flow<FullTransaction>
    fun getSummary(): Flow<TransactionSummary>
    fun getSummary(month: Int, year: Int): Flow<TransactionSummary>
    fun getEarliestDate(): Flow<Date>

    suspend fun insertTransaction(transaction: Transaction)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}