package dev.pontakorn.habitbudget.ui.wallets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListWalletViewModel @Inject constructor(walletRepository: WalletRepository): ViewModel() {

    private val _uiState = MutableStateFlow<List<Wallet>>(emptyList())
    val uiState: StateFlow<List<Wallet>> = _uiState

    init {
        viewModelScope.launch {
            walletRepository.getAll().collect { wallets ->
                _uiState.value = wallets
            }
        }
    }


}