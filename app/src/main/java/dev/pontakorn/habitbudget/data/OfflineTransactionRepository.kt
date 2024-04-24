package dev.pontakorn.habitbudget.data

import dev.pontakorn.habitbudget.utils.DateUtil
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class OfflineTransactionRepository(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override fun getAll(): Flow<List<Transaction>> {
        return transactionDao.getByType()
    }

    override fun getAll(transactionType: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getByType(transactionType)
    }

    override fun getWithMonthAndYear(month: Int, year: Int): Flow<List<Transaction>> {
        val (currentMonth, nextMonth) = DateUtil.getMonthDuration(month, year)
        return transactionDao.getByTypeDuring(currentMonth, nextMonth)
    }

    override fun getWithMonthAndYear(
        month: Int,
        year: Int,
        transactionType: TransactionType
    ): Flow<List<Transaction>> {
        val (currentMonth, nextMonth) = DateUtil.getMonthDuration(month, year)
        return transactionDao.getByTypeDuring(currentMonth, nextMonth, transactionType)
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }
}