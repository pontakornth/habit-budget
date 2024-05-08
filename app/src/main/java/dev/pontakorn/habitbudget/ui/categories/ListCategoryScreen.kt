package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ListCategoryScreen(navController: NavController, viewModel: ListCategoryViewModel = hiltViewModel()) {
    val categories = viewModel.uiState.collectAsState()
    val shouldSelect = navController.currentBackStackEntry?.arguments?.getBoolean("shouldSelect") ?: false
    // TODO: Add categories type
    // TODO: Handle select category
    CategoriesScreen(
        categories = categories.value,
        categoryType = viewModel.categoryType,
        onChangeCategoryType = { categoryType ->
            viewModel.onChangeCategoryType(categoryType)
        },
        onClickCategory = {
            if (shouldSelect) {
                navController.previousBackStackEntry?.savedStateHandle?.set("category_id", it.id)
                navController.popBackStack()
            } else {
                navController.navigate("categories/${it.id}/update")
            }
        },
        shouldSelect = shouldSelect
    )
}