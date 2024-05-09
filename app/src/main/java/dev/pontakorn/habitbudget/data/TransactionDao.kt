package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionDao {

    @Query("SELECT * from `transaction`")
    fun getByType(): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_type = :transactionType")
    fun getByType(transactionType: TransactionType): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_time >= :timeBegin AND transaction_time < :timeEnd")
    fun getByTypeDuring(timeBegin: Date, timeEnd: Date): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_time >= :timeBegin AND transaction_time < :timeEnd AND transaction_type = :transactionType")
    fun getByTypeDuring(timeBegin: Date, timeEnd: Date, transactionType: TransactionType): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_id = :transactionId")
    fun getById(transactionId: Int): Flow<Transaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}