package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun ListWalletScreen(viewModel: ListWalletViewModel) {
    val wallets = viewModel.uiState.collectAsState()
    WalletScreen(
        wallets = wallets.value
    )
}