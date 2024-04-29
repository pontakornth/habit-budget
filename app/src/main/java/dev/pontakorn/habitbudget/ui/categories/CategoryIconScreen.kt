package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.R
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme


@Composable
fun CategoryIconScreen() {
    // TODO: Find something to hold both icon and name
    val iconList: List<ImageVector> = listOf(
        Icons.Filled.ShoppingCart,
        ImageVector.vectorResource(R.drawable.baseline_fastfood_24),
        ImageVector.vectorResource(R.drawable.baseline_local_dining_24),
        ImageVector.vectorResource(R.drawable.baseline_local_drink_24),
        ImageVector.vectorResource(R.drawable.baseline_local_taxi_24),
        ImageVector.vectorResource(R.drawable.baseline_videogame_asset_24),
        ImageVector.vectorResource(R.drawable.baseline_wallet_24),
        ImageVector.vectorResource(R.drawable.baseline_weekend_24),
        ImageVector.vectorResource(R.drawable.baseline_medical_services_24),
        ImageVector.vectorResource(R.drawable.baseline_woman_24),
        ImageVector.vectorResource(R.drawable.baseline_work_24),
        Icons.Filled.Home,
    )
    // Due to lack of arts, icons are hardcoded (or use some files)
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
                    text = "Select Category Icon",
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
                    items(iconList) { icon ->
                        OutlinedIconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = icon, contentDescription = "icon")
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
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Back")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryIconScreenPreview() {
    CategoryIconScreen()
}