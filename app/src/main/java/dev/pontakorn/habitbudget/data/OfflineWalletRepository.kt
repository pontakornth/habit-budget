package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

class OfflineWalletRepository(private val walletDao: WalletDao): WalletRepository  {
    override fun getAll(): Flow<List<Wallet>> {
        return walletDao.getAll()
    }

    override fun getWalletById(walletId: Int): Flow<Wallet> {
        return walletDao.getById(walletId)
    }

    override fun getWalletSummary(): Flow<List<WalletSummary>> {
        return walletDao.getAllWalletSummary()
    }

    override suspend fun insertWallet(wallet: Wallet) {
        walletDao.insert(wallet)
    }

    override suspend fun updateWallet(wallet: Wallet) {
        walletDao.update(wallet)
    }

    override suspend fun deleteWallet(wallet: Wallet) {
        walletDao.delete(wallet)
    }
}