package dev.pontakorn.habitbudget

sealed class DestinationScreens(val route: String) {
    data object Transactions: DestinationScreens("transactions")
    data object HabitTracking: DestinationScreens("habitTracking")
    data object Wallets: DestinationScreens("wallets")
    data object Settings: DestinationScreens("settings")

    data object Categories: DestinationScreens("categories")
    data object AddCategory: DestinationScreens("categories/add")
    data object UpdateCategory: DestinationScreens("categories/{categoryId}/update")

    data object Icons: DestinationScreens("icons")



}