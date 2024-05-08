package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun UpdateCategoryScreen(
    navController: NavController,
    viewModel: UpdateCategoryViewModel = hiltViewModel()
) {
    EditCategoryScreen(navController,viewModel) {
        viewModel.onConfirm()
        navController.popBackStack()
    }
}