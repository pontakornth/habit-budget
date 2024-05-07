package dev.pontakorn.habitbudget.ui.transaction

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.FullTransactionRepository
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fullTransactionRepository: FullTransactionRepository
): EditTransactionViewModel() {
}