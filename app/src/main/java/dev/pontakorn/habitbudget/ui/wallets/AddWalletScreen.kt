package dev.pontakorn.habitbudget.ui.wallets

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import dev.pontakorn.habitbudget.ui.icons.findIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddWalletScreenViewModel @Inject constructor(private val walletRepository: WalletRepository) :
    ViewModel() {

    suspend fun insertWallet(walletName: String, iconName: String) {
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
    var walletName by rememberSaveable { mutableStateOf("") }

    val selectedIconName = navController.currentBackStackEntry?.savedStateHandle?.get<String>("icon_name") ?: "Wallet"
    Log.i("AddWalletScreen", "selectedIconName = $selectedIconName")

    EditWalletScreen(
        walletName = walletName,
        onChangeWalletName = { walletName = it },
        // I hard code this thing. I know it exists.
        currentIcon = findIcon(selectedIconName)!!,
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
                addWalletScreenViewModel.insertWallet(walletName, selectedIconName)
                navController.popBackStack()
            }
        }
    )
}