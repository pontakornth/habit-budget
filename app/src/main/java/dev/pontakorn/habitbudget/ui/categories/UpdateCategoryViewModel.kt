package dev.pontakorn.habitbudget.ui.categories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    categoryRepository: CategoryRepository,
) : EditCategoryViewModel(categoryRepository) {
    private val categoryId = checkNotNull(savedStateHandle.get<Int>("categoryId"))
    private lateinit var category: Category

    init {
        viewModelScope.launch {
            categoryRepository.getById(categoryId).collect {
                category = it
                categoryName = it.name
                iconName = it.iconName
                categoryType = it.categoryType

            }
        }
    }
    override fun onConfirm() {
        viewModelScope.launch {
            categoryRepository.updateCategory(
                Category(
                    id = categoryId,
                    name = categoryName,
                    iconName = iconName,
                    categoryType = categoryType
                )
            )
        }
    }
}