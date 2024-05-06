package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.icons.walletDefaultIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddWalletScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val walletRepository: WalletRepository
) : EditWalletViewModel() {

    suspend fun insertWallet() {
        walletRepository.insertWallet(
            Wallet(
                id = 0,
                name = walletName,
                iconName = iconName
            )
        )
    }

}

@Composable
fun AddWalletScreen(
    navController: NavController,
    addWalletScreenViewModel: AddWalletScreenViewModel = viewModel()
) {

    val selectedIconName =
        navController.currentBackStackEntry?.savedStateHandle?.get<String>("icon_name")

    LaunchedEffect(key1 = selectedIconName) {
        selectedIconName?.let {
            addWalletScreenViewModel.iconName = it
        }
    }




    EditWalletScreenContent(
        walletName = addWalletScreenViewModel.walletName,
        onChangeWalletName = { addWalletScreenViewModel.walletName = it },
        currentIcon = findIcon(addWalletScreenViewModel.iconName) ?: walletDefaultIcon,
        onClickIconButton = {
            navController.navigate(DestinationScreens.Icons.route) {

//                launchSingleTop = true
                restoreState = true
            }
        },
        onBackButtonClick = { navController.popBackStack() },
        onConfirmButtonClick = {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                addWalletScreenViewModel.insertWallet()
                navController.popBackStack()
            }
        }
    )
}