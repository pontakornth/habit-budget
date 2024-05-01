package dev.pontakorn.habitbudget.ui.categories

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun ListCategoryScreen(viewModel: ListCategoryViewModel) {
    val categories = viewModel.uiState.collectAsState()
    CategoriesScreen(
        categories = categories.value,
        onChangeCategoryType = { categoryType ->
            Log.i("example", categoryType.toString())
        }
    )
}