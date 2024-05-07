package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ListWalletScreen(
    navController: NavController,
    viewModel: ListWalletViewModel = hiltViewModel()
) {
    val wallets = viewModel.uiState.collectAsState()

    fun onClickWallet(walletId: Int) {
        // TODO: Find a type-safe way to do this.
        navController.navigate("wallets/$walletId/update")
    }
    WalletScreen(
        wallets = wallets.value,
        onClickWalletCard = { onClickWallet(it) }
    )
}