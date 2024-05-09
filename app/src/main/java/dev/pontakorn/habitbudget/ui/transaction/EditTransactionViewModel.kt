package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import kotlinx.coroutines.launch
import java.util.Date

abstract class EditTransactionViewModel(
    val fullTransactionRepository: FullTransactionRepository,
    val walletRepository: WalletRepository,
    val categoryRepository: CategoryRepository
) : ViewModel() {
    var category by mutableStateOf<Category?>(null)
    var transactionType by mutableStateOf(TransactionType.EXPENSE)
    var sourceWallet by mutableStateOf<Wallet?>(null)

    // Only matter if transfer
    var destinationWallet by mutableStateOf<Wallet?>(null)
    var amount by mutableDoubleStateOf(0.0)
    var transactionDate by mutableStateOf(Date())
    var transactionTime by mutableStateOf(0 to 0)

    fun allowAddTransaction(): Boolean {
        if (transactionType == TransactionType.TRANSFER) {
            if (destinationWallet == null) return false
        } else {
            if (category == null) return false
        }
        if (sourceWallet == null) return false
        if (amount == 0.0) return false
        return true
    }

    fun getSourceWallet(walletId: Int) {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect { wallet ->
                sourceWallet = wallet
            }

        }
    }

    fun getDestinationWallet(walletId: Int) {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect { wallet ->
                destinationWallet = wallet
            }
        }
    }

    fun getCategory(categoryIdFromNavController: Int?) {
        categoryIdFromNavController?.let { categoryId ->
            viewModelScope.launch {
                categoryRepository.getById(categoryId).collect { newCategory ->
                    category = newCategory
                }
            }
        }
    }

    abstract fun onConfirm(): Unit


}