package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date

class OfflineTransactionRepository(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override fun getAll(): Flow<List<Transaction>> {
        return transactionDao.getAll()
    }

    override fun getAll(transactionType: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getAll(transactionType)
    }

    override fun getWithMonthAndYear(month: Int, year: Int): Flow<List<Transaction>> {
        val calendar = Calendar.getInstance()
        // First
        calendar.set(year, month, 1)
        val currentMonth = calendar.time
        // Yes, Calendar can handle this.
        calendar.set(year, month + 1, 1)
        val nextMonth = calendar.time
        return transactionDao.getDuring(currentMonth, nextMonth)
    }

    override fun getWithMonthAndYear(month: Int, year: Int, transactionType: TransactionType): Flow<List<Transaction>> {
        val calendar = Calendar.getInstance()
        // First
        calendar.set(year, month, 1)
        val currentMonth = calendar.time
        // Yes, Calendar can handle this.
        calendar.set(year, month + 1, 1)
        val nextMonth = calendar.time
        return transactionDao.getDuring(currentMonth, nextMonth, transactionType)
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