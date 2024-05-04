package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class EditWalletViewModel : ViewModel() {
    // These are only used in Composable. It is fine.
    var walletName by mutableStateOf("")
    var iconName by mutableStateOf("Wallet")

}