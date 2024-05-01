package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineCategoryRepository @Inject constructor(private val categoryDao: CategoryDao): CategoryRepository {

    override fun getAll(): Flow<List<Category>> {
        return categoryDao.getAll()
    }

    override fun getByType(categoryType: CategoryType): Flow<List<Category>> {
        return categoryDao.getByType(categoryType)
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.update(category)
    }

}