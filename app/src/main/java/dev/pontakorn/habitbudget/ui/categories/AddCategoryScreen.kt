package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddCategoryScreen(
    navController: NavController,
    viewModel: AddCategoryViewModel = hiltViewModel()
) {
    EditCategoryScreen(navController, viewModel) {
        viewModel.onConfirm()
        navController.popBackStack()
    }


}