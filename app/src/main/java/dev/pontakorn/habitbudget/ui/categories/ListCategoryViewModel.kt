package dev.pontakorn.habitbudget.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryRepository
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

    init {
        viewModelScope.launch {
            // TODO: Implement get by type
            categoryRepository.getAll().collect { categories ->
                _uiState.value = categories
            }
        }
    }
}