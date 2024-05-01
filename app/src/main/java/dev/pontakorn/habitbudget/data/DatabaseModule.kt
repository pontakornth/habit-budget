package dev.pontakorn.habitbudget.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        @Provides
        fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
            return appDatabase.categoryDao()
        }

        @Provides
        fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
            return OfflineCategoryRepository(categoryDao)
        }
    }
}