package dev.pontakorn.habitbudget.ui.transaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

abstract class EditTransactionViewModel(
    val savedStateHandle: SavedStateHandle,
    val fullTransactionRepository: FullTransactionRepository,
    val walletRepository: WalletRepository,
    val categoryRepository: CategoryRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(TransactionViewState())
    val uiState: StateFlow<TransactionViewState> = _uiState.asStateFlow()

    protected fun setState(newState: TransactionViewState) {
        _uiState.value = newState
    }

    fun allowAddTransaction(): Boolean {
        if (_uiState.value.transactionType == TransactionType.TRANSFER) {
            if (_uiState.value.destinationWallet == null) return false
        } else {
            if (_uiState.value.category == null) return false
        }
        if (_uiState.value.sourceWallet == null) return false
        if (_uiState.value.amount <= 0.0) return false
        return true
    }

    fun getSourceWallet(walletId: Int) {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect { wallet ->
                _uiState.value = _uiState.value.copy(sourceWallet = wallet)
            }

        }
    }

    fun getDestinationWallet(walletId: Int) {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect { wallet ->
                _uiState.value = _uiState.value.copy(destinationWallet = wallet)
            }
        }
    }

    fun getCategory(categoryIdFromNavController: Int?) {
        categoryIdFromNavController?.let { categoryId ->
            viewModelScope.launch {
                categoryRepository.getById(categoryId).collect { newCategory ->
                    _uiState.value = _uiState.value.copy(category = newCategory)
                }
            }
        }
    }

    fun deleteCategory() {
        _uiState.value = _uiState.value.copy(category = null)
    }

    fun deleteDestinationWallet() {
        _uiState.value = _uiState.value.copy(destinationWallet = null)
    }

    fun setTransactionType(newTransactionType: TransactionType) {
        _uiState.value = _uiState.value.copy(transactionType = newTransactionType)
    }

    abstract fun onConfirm(): Unit
    fun setAmount(newAmount: Double) {
        _uiState.value = _uiState.value.copy(amount = newAmount)
    }

    fun setTransactionDate(newDate: Date) {
        _uiState.value = _uiState.value.copy(transactionDate = newDate)
    }

    fun setTransactionTime(newTime: Pair<Int, Int>) {
        _uiState.value = _uiState.value.copy(transactionTime = newTime)

    }


}