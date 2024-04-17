package dev.pontakorn.habitbudget.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)

    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE category_type = :categoryType")
    fun getByType(categoryType: CategoryType): Flow<List<Category>>
}