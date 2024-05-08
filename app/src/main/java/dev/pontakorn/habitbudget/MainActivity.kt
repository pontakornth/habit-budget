package dev.pontakorn.habitbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.pontakorn.habitbudget.ui.categories.AddCategoryScreen
import dev.pontakorn.habitbudget.ui.categories.ListCategoryScreen
import dev.pontakorn.habitbudget.ui.categories.UpdateCategoryScreen
import dev.pontakorn.habitbudget.ui.habit.HabitTracking
import dev.pontakorn.habitbudget.ui.icons.IconSelectionScreen
import dev.pontakorn.habitbudget.ui.icons.allIcons
import dev.pontakorn.habitbudget.ui.setting.SettingsScreen
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import dev.pontakorn.habitbudget.ui.transaction.AddTransactionScreen
import dev.pontakorn.habitbudget.ui.transaction.ListTransactionScreen
import dev.pontakorn.habitbudget.ui.wallets.AddWalletScreen
import dev.pontakorn.habitbudget.ui.wallets.ListWalletScreen
import dev.pontakorn.habitbudget.ui.wallets.UpdateWalletScreen
import dev.pontakorn.habitbudget.ui.wallets.UpdateWalletScreenViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitBudgetTheme {
                MainScreenWithNavbar()
            }
        }
    }
}

@Composable
fun MainScreenWithNavbar(
) {
    val navController = rememberNavController()
    var navSelectedItem by remember {
        mutableIntStateOf(0)
    }
    val currentRoute = navController.currentBackStackEntryAsState()


    Scaffold(
        floatingActionButton = {
            when (currentRoute.value?.destination?.route) {
                DestinationScreens.Transactions.route -> {
                    FloatingActionButton(onClick = {
                        navController.navigate(DestinationScreens.AddTransaction.route)
                    }) {
                        Icon(Icons.Filled.Add, "Add transaction")
                    }
                }

                DestinationScreens.Wallets.route -> {
                    FloatingActionButton(onClick = {
                        navController.navigate(DestinationScreens.AddWallet.route)

                    }) {
                        Icon(Icons.Filled.Add, "Add wallet")
                    }

                }

                DestinationScreens.Categories.route -> {
                    FloatingActionButton(onClick = {
                        navController.navigate(DestinationScreens.AddCategory.route)

                    }) {
                        Icon(Icons.Filled.Add, "Add category")
                    }

                }
            }
        },
        bottomBar = {
            NavigationBar {
                HabitBudgetNavigationInfo().getAllNavItems().forEachIndexed { index, navInfo ->
                    NavigationBarItem(
                        selected = index == navSelectedItem,
                        onClick = {
                            navSelectedItem = index
                            navController.navigate(navInfo.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navInfo.icon,
                                contentDescription = navInfo.label
                            )
                        },
                        label = {
                            Text(
                                text = navInfo.label, fontSize = 12.sp, textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
        }) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = DestinationScreens.Transactions.route,
            modifier = Modifier.padding(paddingValues)

        ) {
            // TODO: Replace dummy view with view + viewmodel (actual logic)
            composable(route = DestinationScreens.Transactions.route) {
                ListTransactionScreen(navController)
            }
            composable(route = DestinationScreens.AddTransaction.route) {
                AddTransactionScreen(navController)
            }
            composable(
                route = DestinationScreens.UpdateTransaction.route,
                arguments = listOf(
                    navArgument("transactionId") { type = NavType.IntType }
                )
            ) {
                // TODO: Add update transaction screen

            }
            composable(route = DestinationScreens.HabitTracking.route) {
                HabitTracking()
            }
            composable(
                route = DestinationScreens.Wallets.route,
                arguments = listOf(
                    navArgument("selectMode") { type = NavType.IntType; defaultValue = 0 }
                )) {
                ListWalletScreen(navController)
            }
            composable(route = DestinationScreens.AddWallet.route) {
                AddWalletScreen(navController = navController)
            }

            composable(
                route = DestinationScreens.UpdateWallet.route,
                arguments = listOf(
                    navArgument("walletId") { type = NavType.IntType }
                )
            ) {
                val viewModel: UpdateWalletScreenViewModel = hiltViewModel()
                UpdateWalletScreen(navController, viewModel)

            }

            composable(route = DestinationScreens.Icons.route) {
                IconSelectionScreen(navController, usableIcons = allIcons)
            }

            composable(route = DestinationScreens.Settings.route) {
                SettingsScreen(navController)
            }

            composable(route = DestinationScreens.Categories.route, arguments = listOf(
                navArgument("shouldSelect") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )) {
                ListCategoryScreen(navController)
            }

            composable(route = DestinationScreens.AddCategory.route) {
                AddCategoryScreen(navController)
            }

            composable(route = DestinationScreens.UpdateCategory.route, arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.IntType
                }
            )) {
                UpdateCategoryScreen(navController)
            }


        }
    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HabitBudgetTheme {
        MainScreenWithNavbar()
    }
}