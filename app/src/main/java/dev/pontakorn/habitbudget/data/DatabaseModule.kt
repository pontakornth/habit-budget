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

        @Provides
        fun provideWalletDao(appDatabase: AppDatabase): WalletDao {
            return appDatabase.walletDao()
        }

        @Provides
        fun provideWalletRepository(walletDao: WalletDao): WalletRepository {
            return OfflineWalletRepository(walletDao)
        }

        @Provides
        fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
            return appDatabase.transactionDao()
        }

        @Provides
        fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepository {
            return OfflineTransactionRepository(transactionDao)
        }

        @Provides
        fun provideFullTransactionDao(appDatabase: AppDatabase): FullTransactionDao {
            return appDatabase.fullTransactionDao()
        }

        @Provides
        fun provideFullTransactionRepository(fullTransactionDao: FullTransactionDao): FullTransactionRepository {
            return OfflineFullTransactionRepository(fullTransactionDao)
        }
    }
}