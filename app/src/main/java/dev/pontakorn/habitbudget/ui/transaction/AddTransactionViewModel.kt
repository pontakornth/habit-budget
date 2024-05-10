package dev.pontakorn.habitbudget.ui.transaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.StreakRepository
import dev.pontakorn.habitbudget.data.Transaction
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.WalletRepository
import dev.pontakorn.habitbudget.utils.DateUtil.getActualDate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fullTransactionRepository: FullTransactionRepository,
    walletRepository: WalletRepository,
    categoryRepository: CategoryRepository,
    streakRepository: StreakRepository
) : EditTransactionViewModel(
    savedStateHandle,
    fullTransactionRepository,
    walletRepository,
    categoryRepository,
    streakRepository
) {
    override fun onConfirm() {
//        Log.i("AddTransactionViewModel", "Create transaction")
//        Log.i("AddTransactionViewModel", "Category: ${category?.name}")
//        Log.i("AddTransactionViewModel", "TransactionType: $transactionType")
//        Log.i("AddTransactionViewModel", "Amount (baht): $amount")
//        Log.i("AddTransactionViewModel", "Source Wallet: ${sourceWallet?.name}")
//        Log.i("AddTransactionViewModel", "Destination Wallet: ${destinationWallet?.name}")
//        Log.i("AddTransactionViewModel", "Transaction Date: ${getFormattedDate(transactionDate)}")
//        Log.i("AddTransactionViewModel", "Transaction Time: ${transactionTime.first} ${transactionTime.second}")
//
        // Sanitize input to ensure everything works correctly.
        if (uiState.value.transactionType == TransactionType.TRANSFER) {
            deleteCategory()
        } else {
            deleteDestinationWallet()
        }

        val actualTransactionTime =
            getActualDate(
                uiState.value.transactionDate,
                uiState.value.transactionTime.first,
                uiState.value.transactionTime.second
            )



        viewModelScope.launch {

            fullTransactionRepository.insertTransaction(
                Transaction(
                    id = 0,
                    categoryId = uiState.value.category?.id,
                    transactionType = uiState.value.transactionType,
                    // TODO: Prevent user from using null source wallet id
                    sourceWalletId = checkNotNull(uiState.value.sourceWallet?.id),
                    destinationWalletId = uiState.value.destinationWallet?.id,
                    // Satang to baht
                    amount = (uiState.value.amount * 100).toInt(),
                    transactionTime = actualTransactionTime

                )
            )
        }
        createStreak()
    }
}