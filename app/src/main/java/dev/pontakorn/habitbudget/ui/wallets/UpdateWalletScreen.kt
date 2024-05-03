package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import dev.pontakorn.habitbudget.ui.icons.findIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWalletScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val walletRepository: WalletRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow<Wallet?>(null)
    private val walletId = checkNotNull(savedStateHandle.get<Int>("walletId"))
    val walletName = mutableStateOf("")
    val walletIconName = mutableStateOf("Wallet")
    val uiState: StateFlow<Wallet?> = _uiState

    //
    init {
        viewModelScope.launch {
            walletRepository.getWalletById(walletId).collect {
                _uiState.value = it
                walletName.value = it.name
                walletIconName.value = it.iconName
            }
        }
    }

    //
//
    suspend fun updateWallet(selectedIconName: String) {
        walletRepository.updateWallet(
            _uiState.value!!.copy(
                name = walletName.value,
                iconName = selectedIconName
            )
        )
    }

    fun onChangeWalletName(newName: String) {
        walletName.value = newName
    }
}

@Composable
fun UpdateWalletScreen(
    navController: NavController,
    updateWalletScreenViewModel: UpdateWalletScreenViewModel = viewModel()
) {
    val uiState = updateWalletScreenViewModel.uiState.collectAsState()
    val selectedIconName = navController.currentBackStackEntry?.savedStateHandle?.get<String>("icon_name") ?: "Wallet"
    EditWalletScreen(
        title = "Edit Wallet",
        onBackButtonClick = { navController.popBackStack() },
        walletName = updateWalletScreenViewModel.walletName.value,
        currentIcon = findIcon(selectedIconName)!!,
        onClickIconButton = {
                            navController.navigate(DestinationScreens.Icons.route) {
                                restoreState = true
                            }
        },
        onChangeWalletName = {
            updateWalletScreenViewModel.onChangeWalletName(it)
        },
        onConfirmButtonClick = {
            CoroutineScope(Dispatchers.Main).launch {
                updateWalletScreenViewModel.updateWallet(selectedIconName)
                navController.popBackStack()
            }

        }

    )
}