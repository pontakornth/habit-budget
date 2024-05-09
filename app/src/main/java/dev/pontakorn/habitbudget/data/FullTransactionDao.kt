package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date
import androidx.room.Transaction as DatabaseTransaction

@Dao
interface FullTransactionDao {
    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
        "ORDER BY t.transaction_time DESC"

    )
    fun getAllTransactions(): Flow<List<FullTransaction>>

    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_type = :transactionType " +
        "ORDER BY t.transaction_time DESC"
    )
    fun getByType(transactionType: TransactionType): Flow<List<FullTransaction>>

    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_time >= :timeBegin AND t.transaction_time < :timeEnd " +
        "ORDER BY t.transaction_time DESC"
    )
    fun getDuring(timeBegin: Date, timeEnd: Date): Flow<List<FullTransaction>>


    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
                "WHERE t.transaction_time >= :timeBegin AND t.transaction_time < :timeEnd " +
                "AND t.transaction_type = :transactionType " +
        "ORDER BY t.transaction_time DESC"
    )
    fun getDuringByType(
        timeBegin: Date,
        timeEnd: Date,
        transactionType: TransactionType
    ): Flow<List<FullTransaction>>


    @DatabaseTransaction
    @Query(
        "SELECT * from `transaction` t LEFT JOIN category c ON t.category_id = c.id " +
                "LEFT JOIN wallet w ON t.source_wallet_id = w.id OR t.destination_wallet_id = w.id " +
        "WHERE t.id = :id"

    )
    fun getById(id: Int): Flow<FullTransaction>

    @DatabaseTransaction
    @Query(
        "SELECT SUM(IIF(transaction_type = :firstType, amount, 0))  income, " +
                " SUM(IIF(transaction_type = :secondType, amount, 0))  expense " +
                "from `transaction`"
    )
    fun getSummary(
        firstType: TransactionType,
        secondType: TransactionType
    ): Flow<TransactionSummary>

    fun getSummary(): Flow<TransactionSummary> =
        getSummary(TransactionType.INCOME, TransactionType.EXPENSE)


    @DatabaseTransaction
    @Query(
        "SELECT SUM(IIF(transaction_type = :firstType, amount, 0))  income, " +
                " SUM(IIF(transaction_type = :secondType, amount, 0))  expense " +
                "from `transaction` t " +
                "WHERE t.transaction_time >= :timeBegin AND t.transaction_time < :timeEnd"
    )
    fun getSummaryDuring(
        firstType: TransactionType,
        secondType: TransactionType,
        timeBegin: Date,
        timeEnd: Date
    ): Flow<TransactionSummary>

    fun getSummaryDuring(timeBegin: Date, timeEnd: Date): Flow<TransactionSummary> {
        return getSummaryDuring(
            TransactionType.INCOME,
            TransactionType.EXPENSE,
            timeBegin,
            timeEnd
        )
    }


    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}