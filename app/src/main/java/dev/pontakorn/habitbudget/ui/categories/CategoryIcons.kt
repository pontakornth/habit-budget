package dev.pontakorn.habitbudget.ui.categories

import dev.pontakorn.habitbudget.R

data class CategoryIconInfo(
    val iconName: String,
    val resourceId: Int,
)

val allCategoryIcons = listOf<CategoryIconInfo>(
    CategoryIconInfo("Shopping", R.drawable.baseline_shopping_cart_24),
    CategoryIconInfo("Food", R.drawable.baseline_fastfood_24),
    CategoryIconInfo("Dining", R.drawable.baseline_local_dining_24),
    CategoryIconInfo("Drink", R.drawable.baseline_local_drink_24),
    CategoryIconInfo("Taxi", R.drawable.baseline_local_taxi_24),
    CategoryIconInfo("Video Game", R.drawable.baseline_videogame_asset_24),
    CategoryIconInfo("Wallet", R.drawable.baseline_wallet_24),
    CategoryIconInfo("Weekend", R.drawable.baseline_weekend_24),
    CategoryIconInfo("Medical", R.drawable.baseline_medical_services_24),
    CategoryIconInfo("Woman", R.drawable.baseline_woman_24),
    CategoryIconInfo("Work", R.drawable.baseline_work_24),
    CategoryIconInfo("Home", R.drawable.baseline_home_24),

    )