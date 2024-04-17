package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

class OfflineTransactionRepository(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override fun getAll(): Flow<List<Transaction>> {
        return transactionDao.getAll()
    }

    override fun getAll(transactionType: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getAll(transactionType)
    }

    // TODO: Implement get transaction with logic
    override fun getWithMonthAndYear(month: Int, year: Int) {
        TODO("Not yet implemented")
    }

    override fun getWithMonthAndYear(month: Int, year: Int, transactionType: TransactionType) {
        TODO("Not yet implemented")
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