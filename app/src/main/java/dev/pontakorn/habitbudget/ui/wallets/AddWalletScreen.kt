package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddWalletScreenViewModel @Inject constructor(private val walletRepository: WalletRepository) :
    ViewModel() {

    suspend fun insertWallet(walletName: String) {
        walletRepository.insertWallet(
            Wallet(
                id = 0,
                name = walletName,
                iconName = "Wallet"
            )
        )
    }

}

@Composable
fun AddWalletScreen(
    navController: NavController,
    addWalletScreenViewModel: AddWalletScreenViewModel = viewModel()
) {
    var walletName by remember { mutableStateOf("") }

    EditWalletScreen(
        walletName = walletName,
        onChangeWalletName = { walletName = it },
        onClickIconButton = {
            navController.navigate(DestinationScreens.Icons.route)
        },
        onBackButtonClick = { navController.popBackStack() },
        onConfirmButtonClick = {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                addWalletScreenViewModel.insertWallet(walletName)
                navController.popBackStack()
            }
        }
    )
}