package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UpdateTransactionScreen(
    navController: NavController,
    viewModel: UpdateTransactionViewModel = hiltViewModel()
) {

    EditTransactionScreen(
        navController = navController,
        viewModel = viewModel,
        title = "Edit Transaction"
    )
}