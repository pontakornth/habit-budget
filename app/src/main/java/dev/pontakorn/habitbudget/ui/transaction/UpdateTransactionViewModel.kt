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
import dev.pontakorn.habitbudget.utils.DateUtil
import dev.pontakorn.habitbudget.utils.DateUtil.getHourAndMinute
import dev.pontakorn.habitbudget.utils.DateUtil.getPureDate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTransactionViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    fullTransactionRepository: FullTransactionRepository,
    walletRepository: WalletRepository,
    categoryRepository: CategoryRepository
) : EditTransactionViewModel(fullTransactionRepository, walletRepository, categoryRepository) {

    val transactionId = checkNotNull(savedStateHandle.get<Int>("transactionId"))

    init {
        viewModelScope.launch {
            fullTransactionRepository.getById(transactionId).collect { fullTransaction ->
                category = fullTransaction.category
                transactionTime = getHourAndMinute(fullTransaction.transaction.transactionTime)

                sourceWallet = fullTransaction.sourceWallet
                destinationWallet = fullTransaction.destinationWallet
                amount = fullTransaction.transaction.amount / 100.0
                transactionDate = getPureDate(fullTransaction.transaction.transactionTime)


            }
        }
        Log.i("UpdateTransactionViewModel", "transactionId: $transactionId")
    }

    override fun onConfirm() {
        // Sanitize input to ensure everything works correctly.
        if (transactionType == TransactionType.TRANSFER) {
            category = null
        } else {
            destinationWallet = null
        }

        val actualTransactionTime =
            DateUtil.getActualDate(transactionDate, transactionTime.first, transactionTime.second)
        viewModelScope.launch {

            fullTransactionRepository.updateTransaction(
                Transaction(
                    id = transactionId,
                    categoryId = category?.id,
                    transactionType = transactionType,
                    // TODO: Prevent user from using null source wallet id
                    sourceWalletId = checkNotNull(sourceWallet?.id),
                    destinationWalletId = destinationWallet?.id,
                    // Satang to baht
                    amount = (amount * 100).toInt(),
                    transactionTime = actualTransactionTime

                )
            )
        }
    }

}
