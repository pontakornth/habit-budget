package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.pontakorn.habitbudget.data.CategoryType

abstract class EditCategoryViewModel() : ViewModel() {
    var categoryName by mutableStateOf("")
    private set
    var categoryIcon by mutableStateOf(allCategoryIcons[0])
    private set
    var categoryType by mutableStateOf(CategoryType.EXPENSE)
    private set

    fun onChangeCategoryName(newCategoryName: String) {
        categoryName = newCategoryName
    }

    fun onChangeCategoryIcon(newCategoryIcon: CategoryIconInfo) {
        categoryIcon = newCategoryIcon
    }

    fun onChangeCategoryType(newCategoryType: CategoryType) {
        categoryType = newCategoryType
    }

    fun onIconButtonClick() {
        TODO("Find a way to transition to icon selection screen")
    }

    abstract fun onConfirm(): Unit

}