package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import java.util.Date

abstract class EditTransactionViewModel : ViewModel() {
    var category by mutableStateOf<Category?>(null)
    var transactionType by mutableStateOf(TransactionType.EXPENSE)
    var sourceWallet by mutableStateOf<Wallet?>(null)
    // Only matter if transfer
    var destinationWallet by mutableStateOf<Wallet?>(null)
    var amount by mutableDoubleStateOf(0.0)
    var transactionDate by mutableStateOf(Date())

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


}