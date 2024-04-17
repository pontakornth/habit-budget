package dev.pontakorn.habitbudget.data

import kotlinx.coroutines.flow.Flow

class OfflineCategoryRepository(private val categoryDao: CategoryDao): CategoryDao {
    override suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    override suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    override suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }

    override fun getAll(): Flow<List<Category>> {
        return categoryDao.getAll()
    }

    override fun getByType(categoryType: CategoryType): Flow<List<Category>> {
        return categoryDao.getByType(categoryType)
    }

}