package dev.pontakorn.habitbudget.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.TransactionSummary
import dev.pontakorn.habitbudget.ui.icons.categoryDefaultIcon
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.icons.walletDefaultIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTransactionViewModel @Inject constructor(fullTransactionRepository: FullTransactionRepository) :
    ViewModel() {
    private var _transactionSummary = MutableStateFlow<TransactionSummary?>(null)
    val transactionSummary = _transactionSummary.asStateFlow()
    private var _transactionList = MutableStateFlow<List<TransactionDisplayItem>>(emptyList())
    val transactionList = _transactionList.asStateFlow()


    init {
        viewModelScope.launch {
            // TODO: Handle month and year
            fullTransactionRepository.getAll().map {
                it.map { transaction ->
                    TransactionDisplayItem(
                        transactionTitle = transaction.category.name,
                        transactionDate = transaction.transaction.transactionTime,
                        // TODO: Find a better way to do this.
                        transactionAmount = transaction.transaction.amount / 100.0,
                        transactionIcon = findIcon(transaction.category.iconName)
                            ?: categoryDefaultIcon,
                        transactionSourceWalletIcon = findIcon(transaction.sourceWallet.iconName)
                            ?: walletDefaultIcon

                    )
                }
            }.collect {
                _transactionList.value = it
            }
        }
        viewModelScope.launch {
            fullTransactionRepository.getSummary().collect {
                _transactionSummary.value = it
            }

        }
    }


}