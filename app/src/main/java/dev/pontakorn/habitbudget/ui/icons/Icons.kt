package dev.pontakorn.habitbudget.ui.icons

import dev.pontakorn.habitbudget.R

data class IconInfo(
    val iconName: String,
    val resourceId: Int,
)

val allIcons = listOf<IconInfo>(
    IconInfo("Shopping", R.drawable.baseline_shopping_cart_24),
    IconInfo("Food", R.drawable.baseline_fastfood_24),
    IconInfo("Dining", R.drawable.baseline_local_dining_24),
    IconInfo("Drink", R.drawable.baseline_local_drink_24),
    IconInfo("Taxi", R.drawable.baseline_local_taxi_24),
    IconInfo("Video Game", R.drawable.baseline_videogame_asset_24),
    IconInfo("Wallet", R.drawable.baseline_wallet_24),
    IconInfo("Weekend", R.drawable.baseline_weekend_24),
    IconInfo("Medical", R.drawable.baseline_medical_services_24),
    IconInfo("Woman", R.drawable.baseline_woman_24),
    IconInfo("Work", R.drawable.baseline_work_24),
    IconInfo("Home", R.drawable.baseline_home_24),

    )