package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun ListCategoryScreen(viewModel: ListCategoryViewModel) {
    val categories = viewModel.uiState.collectAsState()
}