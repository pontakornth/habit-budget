package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAll(): Flow<List<Category>>
    fun getByType(categoryType: CategoryType): Flow<List<Category>>
    fun getById(id: Int): Flow<Category>
    suspend fun insertCategory(category: Category)

    suspend fun deleteCategory(category: Category)
    suspend fun updateCategory(category: Category)
}