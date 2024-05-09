package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wallet: Wallet)

    @Update
    suspend fun update(wallet: Wallet)

    @Delete
    suspend fun delete(wallet: Wallet)

    @Query("SELECT * from wallet")

    fun getAll(): Flow<List<Wallet>>


    @Query("SELECT * from wallet WHERE id = :walletId")

    fun getById(walletId: Int): Flow<Wallet>

    // This code is from Gemini. Hohoho
    @Query("SELECT w.id as walletId, " +
            "COALESCE(SUM(CASE WHEN t.transaction_type = :incomeType THEN t.amount ELSE 0 END), 0) " +
            "+ SUM(CASE WHEN t.destination_wallet_id = w.id AND t.transaction_type = :transferType THEN t.amount ELSE 0 END) " +
            "- COALESCE(SUM(CASE WHEN t.transaction_type = :expenseType THEN t.amount ELSE 0 END), 0) " +
            "- SUM(CASE WHEN t.source_wallet_id AND t.transaction_type = :transferType = w.id THEN t.amount ELSE 0 END) AS balance " +
            "FROM wallet w LEFT JOIN `transaction` t ON w.id = t.destination_wallet_id OR w.id = t.source_wallet_id")
    fun getAllWalletSummary(
        incomeType: TransactionType = TransactionType.INCOME,
        expenseType: TransactionType = TransactionType.EXPENSE,
        transferType: TransactionType = TransactionType.TRANSFER
    ): Flow<List<WalletSummary>>
}