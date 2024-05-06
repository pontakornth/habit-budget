package dev.pontakorn.habitbudget.ui.wallets

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
class UpdateWalletScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val walletRepository: WalletRepository
) : EditWalletViewModel() {
    private val walletId = checkNotNull(savedStateHandle.get<Int>("walletId"))
    private lateinit var wallet: Wallet


    //
    init {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect {
                wallet = it
                walletName = it.name
                iconName = it.iconName
            }
            // Assume it is a valid icon.
            Log.i("UpdateWalletScreenViewModel", "Getting icon")
            val iconNameFromSelectionScreen = savedStateHandle.get<String>("icon_name")
            if (iconNameFromSelectionScreen != null) {
                Log.i("UpdateWalletScreenViewModel", "icon_name: $iconNameFromSelectionScreen")
                iconName = iconNameFromSelectionScreen
            } else {
                Log.i("UpdateWalletScreenViewModel", "icon_name: null")
            }
        }
    }

    //
//
    suspend fun updateWallet() {
        walletRepository.updateWallet(
            wallet.copy(
                name = walletName,
                iconName = iconName
            )
        )
    }

}

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
