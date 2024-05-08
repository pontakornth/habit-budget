package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
import dev.pontakorn.habitbudget.data.CategoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Category>>(emptyList())
    val uiState: StateFlow<List<Category>> = _uiState
    var categoryType by mutableStateOf(CategoryType.EXPENSE)

    fun onChangeCategoryType(newCategoryType: CategoryType) {
        categoryType = newCategoryType
        getCategories()
    }

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoryRepository.getByType(categoryType).collect { categories ->
                _uiState.value = categories
            }
        }
    }
}