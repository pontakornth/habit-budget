package dev.pontakorn.habitbudget.ui.transaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import dev.pontakorn.habitbudget.data.StreakRepository
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

    val transactionId = checkNotNull(savedStateHandle.get<Int>("transactionId"))
    var transactionObject: Transaction? = null

    init {
        viewModelScope.launch {
            fullTransactionRepository.getById(transactionId).collect { fullTransaction ->
                if (fullTransaction == null) return@collect
                setState(
                    TransactionViewState(
                        transactionType = fullTransaction.transaction.transactionType,
                        category = fullTransaction.category,
                        transactionTime = getHourAndMinute(fullTransaction.transaction.transactionTime),
                        sourceWallet = fullTransaction.sourceWallet,
                        destinationWallet = fullTransaction.destinationWallet,
                        amount = fullTransaction.transaction.amount / 100.0,
                        transactionDate = getPureDate(fullTransaction.transaction.transactionTime),
                    )
                )
                transactionObject = fullTransaction.transaction


            }
        }
    }

    override fun onConfirm() {
        // Sanitize input to ensure everything works correctly.
        if (uiState.value.transactionType == TransactionType.TRANSFER) {
            deleteCategory()
        } else {
            deleteDestinationWallet()
        }

        val actualTransactionTime =
            DateUtil.getActualDate(
                uiState.value.transactionDate,
                uiState.value.transactionTime.first,
                uiState.value.transactionTime.second
            )
        viewModelScope.launch {

            fullTransactionRepository.updateTransaction(
                Transaction(
                    id = transactionId,
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

    fun onDelete(navController: NavController) {
        viewModelScope.launch {
            navController.navigate(DestinationScreens.Transactions.route)
            transactionObject?.let {
                fullTransactionRepository.deleteTransaction(it)
            }

        }
    }

}
