package dev.pontakorn.habitbudget.ui.categories

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    categoryRepository: CategoryRepository
) : EditCategoryViewModel(categoryRepository) {
    override fun onConfirm() {
        // Save new category to the database
        viewModelScope.launch {
            categoryRepository.insertCategory(
                Category(
                    id = 0, // Default value
                    name = categoryName,
                    iconName = iconName,
                    categoryType = categoryType,
                )
            )
        }
    }
}