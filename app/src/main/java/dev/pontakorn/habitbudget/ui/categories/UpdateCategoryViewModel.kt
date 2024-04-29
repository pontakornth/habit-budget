package dev.pontakorn.habitbudget.ui.categories

import androidx.lifecycle.viewModelScope
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
import kotlinx.coroutines.launch

class UpdateCategoryViewModel(
    private val categoryRepository: CategoryRepository,
) : EditCategoryViewModel() {
    override fun onConfirm() {
        viewModelScope.launch {
            categoryRepository.updateCategory(
                Category(
                    // TODO: Use existing category
                    id = 0,
                    name = categoryName,
                    categoryType = categoryType
                )
            )
        }
    }
}