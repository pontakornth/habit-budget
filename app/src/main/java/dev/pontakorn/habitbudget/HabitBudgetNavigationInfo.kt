package dev.pontakorn.habitbudget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class HabitBudgetNavigationInfo(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Star,
    val route: String = ""
) {
    fun getAllNavItems(): List<HabitBudgetNavigationInfo> {
        return listOf(
            HabitBudgetNavigationInfo(
                label = "Transaction",
                icon = Icons.Outlined.ThumbUp,
                route = DestinationScreens.Transactions.route
            ),
            HabitBudgetNavigationInfo(
                label = "Habit",
                icon = Icons.Outlined.DateRange,
                route = DestinationScreens.HabitTracking.route
            ),
            HabitBudgetNavigationInfo(
                label = "Wallet",
                icon = Icons.Outlined.AccountBox,
                route = DestinationScreens.Wallets.route
            ),
            HabitBudgetNavigationInfo(
                label = "Settings",
                icon = Icons.Outlined.Settings,
                route = DestinationScreens.Settings.route
            )
        )

    }
}
