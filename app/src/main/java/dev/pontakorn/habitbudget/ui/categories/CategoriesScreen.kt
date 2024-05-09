package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    categories: List<Category> = emptyList(),
    categoryType: CategoryType = CategoryType.EXPENSE,
    onChangeCategoryType: (CategoryType) -> Unit = {},
    onClickCategory: (Category) -> Unit = {},
    shouldSelect: Boolean = false
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
                    FilterChip(
                        enabled = !shouldSelect,
                        selected = categoryType == CategoryType.EXPENSE,
                        onClick = { onChangeCategoryType(CategoryType.EXPENSE) },
                        label = {
                            Text(text = "Expense")
                        },
                        leadingIcon = if (categoryType == CategoryType.EXPENSE) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        })
                    FilterChip(
                        enabled = !shouldSelect,
                        selected = categoryType == CategoryType.INCOME,
                        onClick = { onChangeCategoryType(CategoryType.INCOME) },
                        label = {
                            Text(text = "Income")
                        },
                        leadingIcon = if (categoryType == CategoryType.INCOME) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    userScrollEnabled = true
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