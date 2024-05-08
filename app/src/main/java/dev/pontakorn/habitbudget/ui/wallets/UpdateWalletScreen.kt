package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.icons.walletDefaultIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UpdateWalletScreen(
    navController: NavController,
    updateWalletScreenViewModel: UpdateWalletScreenViewModel = viewModel()
) {
    val iconNameFromNavController = navController.currentBackStackEntry?.savedStateHandle?.get<String>("icon_name")
    LaunchedEffect(key1 = iconNameFromNavController) {
        iconNameFromNavController?.let {
            updateWalletScreenViewModel.iconName = it
        }
    }
    EditWalletScreenContent(
        title = "Edit Wallet",
        onBackButtonClick = { navController.popBackStack() },
        walletName = updateWalletScreenViewModel.walletName,
        currentIcon = findIcon(updateWalletScreenViewModel.iconName) ?: walletDefaultIcon,
        onClickIconButton = {
            navController.navigate(DestinationScreens.Icons.route) {
                restoreState = true
            }
        },
        onChangeWalletName = {
            updateWalletScreenViewModel.walletName = it
        },
        onConfirmButtonClick = {
            CoroutineScope(Dispatchers.Main).launch {
                updateWalletScreenViewModel.updateWallet()
                navController.popBackStack()
            }

        }

    )
}
