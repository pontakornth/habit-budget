package dev.pontakorn.habitbudget.ui.categories

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ListCategoryScreen(navController: NavController, viewModel: ListCategoryViewModel = hiltViewModel()) {
    val categories = viewModel.uiState.collectAsState()
    CategoriesScreen(
        categories = categories.value,
        onChangeCategoryType = { categoryType ->
            Log.i("example", categoryType.toString())
        }
    )
}