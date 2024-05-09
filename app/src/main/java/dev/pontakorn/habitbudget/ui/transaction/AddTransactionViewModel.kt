package dev.pontakorn.habitbudget.ui.transaction

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.Transaction
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.WalletRepository
import dev.pontakorn.habitbudget.utils.DateUtil.getActualDate
import dev.pontakorn.habitbudget.utils.DateUtil.getFormattedDate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fullTransactionRepository: FullTransactionRepository,
    walletRepository: WalletRepository,
    categoryRepository: CategoryRepository
) : EditTransactionViewModel(fullTransactionRepository, walletRepository, categoryRepository) {
    override fun onConfirm() {
        Log.i("AddTransactionViewModel", "Create transaction")
        Log.i("AddTransactionViewModel", "Category: ${category?.name}")
        Log.i("AddTransactionViewModel", "TransactionType: $transactionType")
        Log.i("AddTransactionViewModel", "Amount (baht): $amount")
        Log.i("AddTransactionViewModel", "Source Wallet: ${sourceWallet?.name}")
        Log.i("AddTransactionViewModel", "Destination Wallet: ${destinationWallet?.name}")
        Log.i("AddTransactionViewModel", "Transaction Date: ${getFormattedDate(transactionDate)}")
        Log.i("AddTransactionViewModel", "Transaction Time: ${transactionTime.first} ${transactionTime.second}")

        // Sanitize input to ensure everything works correctly.
        if (transactionType == TransactionType.TRANSFER) {
            category = null
        } else {
            destinationWallet = null
        }

        val actualTransactionTime = getActualDate(transactionDate, transactionTime.first, transactionTime.second)



        viewModelScope.launch {

            fullTransactionRepository.insertTransaction(
                Transaction(
                    id = 0,
                    categoryId = category?.id,
                    transactionType = transactionType,
                    // TODO: Prevent user from using null source wallet id
                    sourceWalletId = checkNotNull( sourceWallet?.id),
                    destinationWalletId = destinationWallet?.id,
                    // Satang to baht
                    amount = (amount * 100).toInt(),
                    transactionTime = actualTransactionTime

                    )
            )
        }

    }
}