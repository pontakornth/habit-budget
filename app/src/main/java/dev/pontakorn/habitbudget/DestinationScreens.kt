package dev.pontakorn.habitbudget

sealed class DestinationScreens(val route: String) {
    data object Transactions: DestinationScreens("transactions")
    data object AddTransaction: DestinationScreens("transactions/add")
    data object UpdateTransaction: DestinationScreens("transactions/{transactionId}/update")
    data object HabitTracking: DestinationScreens("habitTracking")
    data object Wallets: DestinationScreens("wallets?selectMode={selectMode}")
    data object AddWallet: DestinationScreens("wallets/add")
    data object UpdateWallet: DestinationScreens("wallets/{walletId}/update")
    data object Settings: DestinationScreens("settings")

    data object Categories: DestinationScreens("categories?shouldSelect={shouldSelect}&categoryType={categoryType}")
    data object AddCategory: DestinationScreens("categories/add")
    data object UpdateCategory: DestinationScreens("categories/{categoryId}/update")

    data object Icons: DestinationScreens("icons")




}