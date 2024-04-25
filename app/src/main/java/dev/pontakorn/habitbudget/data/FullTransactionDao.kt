package dev.pontakorn.habitbudget.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction as DatabaseTransaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FullTransactionDao {
    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id"

    )
    fun getAllTransactions(): Flow<List<FullTransaction>>

    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_type = :transactionType"
    )
    fun getByType(transactionType: TransactionType): Flow<List<FullTransaction>>

    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_time >= :timeBegin AND t.transaction_time < :timeEnd"
    )
    fun getDuring(timeBegin: Date, timeEnd: Date): Flow<List<FullTransaction>>


    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_time >= :timeBegin AND t.transaction_time < :timeEnd " +
                "AND t.transaction_type = :transactionType"
    )
    fun getDuringByType(
        timeBegin: Date,
        timeEnd: Date,
        transactionType: TransactionType
    ): Flow<List<FullTransaction>>

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}