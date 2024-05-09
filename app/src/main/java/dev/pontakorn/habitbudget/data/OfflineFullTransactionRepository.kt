package dev.pontakorn.habitbudget.data

import dev.pontakorn.habitbudget.utils.DateUtil
import kotlinx.coroutines.flow.Flow
import java.util.Date

class OfflineFullTransactionRepository(private val fullTransactionDao: FullTransactionDao): FullTransactionRepository {
    override fun getAll(): Flow<List<FullTransaction>> {
        return fullTransactionDao.getAllTransactions()
    }

    override fun getAll(transactionType: TransactionType): Flow<List<FullTransaction>> {
        return fullTransactionDao.getByType(transactionType)
    }

    override fun getWithMonthAndYear(month: Int, year: Int): Flow<List<FullTransaction>> {
        val (currentMonth, nextMonth) = DateUtil.getMonthDuration(month, year)
        return fullTransactionDao.getDuring(currentMonth, nextMonth)
    }

    override fun getWithMonthAndYear(
        month: Int,
        year: Int,
        transactionType: TransactionType
    ): Flow<List<FullTransaction>> {
        val (currentMonth, nextMonth) = DateUtil.getMonthDuration(month, year)
        return fullTransactionDao.getDuringByType(currentMonth, nextMonth, transactionType)
    }

    override fun getById(transactionId: Int): Flow<FullTransaction> {
        return fullTransactionDao.getById(transactionId)
    }

    override fun getSummary(): Flow<TransactionSummary> {
        return fullTransactionDao.getSummary()
    }

    override fun getSummary(month: Int, year: Int): Flow<TransactionSummary> {
        val (currentMonth, nextMonth) = DateUtil.getMonthDuration(month, year)
        return fullTransactionDao.getSummaryDuring(currentMonth, nextMonth)
    }

    override fun getEarliestDate(): Flow<Date> {
        return fullTransactionDao.getEarliestTransactionTime()
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        fullTransactionDao.insertTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        fullTransactionDao.updateTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        fullTransactionDao.deleteTransaction(transaction)
    }
}