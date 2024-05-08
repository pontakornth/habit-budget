package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryType
import dev.pontakorn.habitbudget.ui.icons.categoryDefaultIcon
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun CategoriesScreen(
    categories: List<Category> = emptyList(),
    onChangeCategoryType: (CategoryType) -> Unit = {},
    onClickCategory: (Category) -> Unit = {},
) {
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {
                Text(
                    text = "Categories",
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(onClick = { onChangeCategoryType(CategoryType.INCOME) }) {
                        Text(text = "Income")
                    }
                    Button(onClick = { onChangeCategoryType(CategoryType.EXPENSE) }) {
                        Text(text = "Expense")
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        CategoryItemView(
                            categoryIcon = findIcon(category.iconName) ?: categoryDefaultIcon,
                            categoryName = category.name,
                            onClick = {
                                onClickCategory(category)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    CategoriesScreen()
}