package dev.pontakorn.habitbudget.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Category::class, Transaction::class, Wallet::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDatabase.RenameIconName::class),
        AutoMigration(from = 2, to = 3),
    AutoMigration(from = 3, to = 4, spec = AppDatabase.RenamePrimaryColumn::class)
    ]
)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase : RoomDatabase() {
    @RenameColumn.Entries(
        RenameColumn(
            tableName = "Wallet",
            fromColumnName = "iconName",
            toColumnName = "icon_name"
        )
    )
    class RenameIconName : AutoMigrationSpec

    @RenameColumn.Entries(
        RenameColumn(
            tableName = "Transaction",
            fromColumnName = "id",
            toColumnName = "transaction_id"
        ),
        RenameColumn(
            tableName = "Wallet",
            fromColumnName = "id",
            toColumnName = "wallet_id"
        ),
        RenameColumn(
            tableName = "Category",
            fromColumnName = "id",
            toColumnName = "category_id"
        )
    )
    class RenamePrimaryColumn : AutoMigrationSpec

    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun fullTransactionDao(): FullTransactionDao
    abstract fun walletDao(): WalletDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}