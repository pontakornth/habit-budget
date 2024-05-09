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
    val walletSummaries = viewModel.walletSummary.collectAsState()

    fun onClickWallet(walletId: Int) {
        // TODO: Find a type-safe way to do this.
        val selectMode = navController.currentBackStackEntry?.arguments?.getInt("selectMode") ?: 0
        if (selectMode != 0) {
            navController.previousBackStackEntry?.savedStateHandle?.run {
                set("wallet_id", walletId)
                set("mode", selectMode)
            }
            navController.popBackStack()
        } else {
            navController.navigate("wallets/$walletId/update")
        }
    }
    WalletScreen(
        wallets = wallets.value,
        walletSummaries = walletSummaries.value,
        onClickWalletCard = { onClickWallet(it) }
    )
}