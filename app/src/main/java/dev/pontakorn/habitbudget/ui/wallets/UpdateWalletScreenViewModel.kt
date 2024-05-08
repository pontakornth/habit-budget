package dev.pontakorn.habitbudget.ui.wallets

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
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