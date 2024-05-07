package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddTransactionScreen(
    navController: NavController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    EditTransactionScreen(
        navController = navController,
        viewModel = viewModel,
        title = "Add Transaction"
    )
}