package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    fun getAll(): Flow<List<Wallet>>
    fun getWalletById(walletId: Int): Flow<Wallet>
    suspend fun insertWallet(wallet: Wallet)
    suspend fun updateWallet(wallet: Wallet)
    suspend fun deleteWallet(wallet: Wallet)


}