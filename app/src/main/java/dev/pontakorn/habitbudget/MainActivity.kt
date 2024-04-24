package dev.pontakorn.habitbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitBudgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun MainScreenWithNavbar() {
    val navController = rememberNavController()
    var navSelectedItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(bottomBar = {
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
                    icon = { Icon(imageVector = navInfo.icon, contentDescription = navInfo.label) },
                    label = {
                        Text(text = navInfo.label)
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
            composable(route = DestinationScreens.Transactions.route) {
                TransactionsScreen()
            }
            composable(route = DestinationScreens.HabitTracking.route) {
                // TODO: Add habit tracking screen
            }
            composable(route = DestinationScreens.Wallets.route) {
                // TODO: Add wallet screen
            }
            composable(route = DestinationScreens.Settings.route) {
                // TODO: Add setting screen
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