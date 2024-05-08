package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pontakorn.habitbudget.ui.icons.IconInfo
import dev.pontakorn.habitbudget.ui.icons.categoryDefaultIcon
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun CategoryItemView(
    categoryIcon: IconInfo,
    categoryName: String,
    onClick: () -> Unit = {}
) {
    HabitBudgetTheme {
        // TODO: Add click event
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onClick() },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Box(
                    modifier = Modifier.border(BorderStroke(2.dp, Color.Black))
                ) {
                    Icon(
                        painter = painterResource(id = categoryIcon.resourceId),
                        contentDescription = categoryIcon.iconName,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(text = categoryName)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletItemViewPreview() {
    CategoryItemView(
        categoryIcon = categoryDefaultIcon,
        categoryName = "Household",
    )
}