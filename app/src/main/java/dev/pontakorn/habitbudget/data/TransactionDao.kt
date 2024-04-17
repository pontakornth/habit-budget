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
    fun getAll(): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_type = :transactionType")
    fun getAll(transactionType: TransactionType): Flow<List<Transaction>>

    @Query("SELECT * from `transaction` WHERE transaction_time >= :timeAfter AND transaction_time <= :timeBefore")
    fun getDuring(timeAfter: Date, timeBefore: Date)

    @Query("SELECT * from `transaction` WHERE transaction_time >= :timeAfter AND transaction_time <= :timeBefore AND transaction_type = :transactionType")
    fun getDuring(timeAfter: Date, timeBefore: Date, transactionType: TransactionType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}