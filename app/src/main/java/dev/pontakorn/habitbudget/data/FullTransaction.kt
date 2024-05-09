package dev.pontakorn.habitbudget.data

import androidx.room.Embedded
import androidx.room.Relation

data class FullTransaction(
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "category_id"
    )
    val category: Category?,

    @Relation(
        parentColumn = "source_wallet_id",
        entityColumn = "wallet_id"
    )
    val sourceWallet: Wallet,

    @Relation(
        parentColumn = "destination_wallet_id",
        entityColumn = "wallet_id"
    )
    val destinationWallet: Wallet?
)
