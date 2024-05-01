package dev.pontakorn.habitbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pontakorn.habitbudget.ui.categories.ListCategoryViewModel
import dev.pontakorn.habitbudget.ui.habit.HabitTracking
import dev.pontakorn.habitbudget.ui.setting.SettingsScreen
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import dev.pontakorn.habitbudget.ui.transaction.TransactionScreen
import dev.pontakorn.habitbudget.ui.wallets.ListWalletViewModel
import dev.pontakorn.habitbudget.ui.wallets.WalletScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val listCategoryViewModel: ListCategoryViewModel by viewModels()
//    private val listWalletViewModel: ListWalletViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitBudgetTheme {
                MainScreenWithNavbar(
                    listCategoryViewModel = listCategoryViewModel,
//                    listWalletViewModel = listWalletViewModel
                )
            }
        }
    }
}

@Composable
fun MainScreenWithNavbar(
    listCategoryViewModel: ListCategoryViewModel? = null,
    listWalletViewModel: ListWalletViewModel? = null
) {
    val navController = rememberNavController()
    var navSelectedItem by remember {
        mutableIntStateOf(0)
    }
    val currentRoute = navController.currentBackStackEntryAsState()


    Scaffold(
        floatingActionButton = {
            if (currentRoute.value?.destination?.route == DestinationScreens.Transactions.route) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, "Transaction")
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
                TransactionScreen(
                    income = 999.0,
                    expense = 999.0,
                    remaining = 0.0,

                    )
//                ListCategoryScreen(listCategoryViewModel!!)
            }
            composable(route = DestinationScreens.HabitTracking.route) {
                HabitTracking()
            }
            composable(route = DestinationScreens.Wallets.route) {
                WalletScreen()
//                ListWalletScreen(listWalletViewModel!!)
            }

            composable(route = DestinationScreens.Settings.route) {
                SettingsScreen()
            }

        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HabitBudgetTheme {
        MainScreenWithNavbar()
    }
}