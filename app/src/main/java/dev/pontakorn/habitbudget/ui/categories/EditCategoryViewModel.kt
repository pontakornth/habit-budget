package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.CategoryType
import dev.pontakorn.habitbudget.ui.icons.categoryDefaultIcon

abstract class EditCategoryViewModel(val categoryRepository: CategoryRepository) : ViewModel() {
    var categoryName by mutableStateOf("")
    var iconName by mutableStateOf(categoryDefaultIcon.iconName)
    var categoryType by mutableStateOf(CategoryType.EXPENSE)



    abstract fun onConfirm(): Unit

}