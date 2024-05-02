package dev.pontakorn.habitbudget.ui.icons

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme


@Composable
fun IconSelectionScreen(
    navController: NavController = rememberNavController(),
    title: String = "Choose Category Icon",
    usableIcons: List<IconInfo>,
    onSelectCategoryIcon: (IconInfo) -> Unit = {},
    onBackButtonClick: () -> Unit = {}
) {
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle()
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    items(usableIcons) { icon ->
                        OutlinedIconButton(onClick = {
                            onSelectCategoryIcon(icon)
                            navController.previousBackStackEntry?.savedStateHandle?.set("icon_name", icon.iconName)
                            navController.popBackStack()
                            Log.i("IconSelectionScreen", "Icon selected: ${icon.iconName}")
                        }) {
                            Icon(
                                painter = painterResource(icon.resourceId),
                                contentDescription = icon.iconName
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(onClick = onBackButtonClick) {
                        Text(text = "Back")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun IconSelectionScreenPreview() {
    IconSelectionScreen(usableIcons = allIcons)
}