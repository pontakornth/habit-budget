package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.data.CategoryType
import dev.pontakorn.habitbudget.ui.icons.IconInfo
import dev.pontakorn.habitbudget.ui.icons.categoryDefaultIcon
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun EditCategoryScreen(
    navController: NavController,
    viewModel: EditCategoryViewModel,
    onConfirmButtonClick: () -> Unit
) {

    val selectedIconName =
        navController.currentBackStackEntry?.savedStateHandle?.get<String>("icon_name")

    LaunchedEffect(key1 = selectedIconName) {
        selectedIconName?.let {
            viewModel.iconName = it
        }
    }
    EditCategoryScreenContent(
        title = "Edit Category",
        categoryName = viewModel.categoryName,
        onChangeCategoryName = {
            viewModel.categoryName = it
        },
        currentIcon = findIcon(viewModel.iconName) ?: categoryDefaultIcon,
        onClickIconButton = {
            navController.navigate(DestinationScreens.Icons.route) {
                restoreState = true
            }
        },
        categoryType = viewModel.categoryType,
        onChangeCategoryType = {
            viewModel.categoryType = it
        },
        onBackButtonClick = {
            navController.popBackStack()
        },
        onConfirmButtonClick = onConfirmButtonClick,
    )
}

@Composable
fun EditCategoryScreenContent(
    title: String = "Add Category",
    categoryName: String = "",
    onChangeCategoryName: (String) -> Unit = {},
    categoryType: CategoryType = CategoryType.EXPENSE,
    onChangeCategoryType: (CategoryType) -> Unit = {},
    currentIcon: IconInfo = categoryDefaultIcon,
    onClickIconButton: () -> Unit = {},
    onBackButtonClick: () -> Unit = {},
    onConfirmButtonClick: () -> Unit = {}
) {
    val categoryTypes = listOf(CategoryType.EXPENSE, CategoryType.INCOME)
    var typeDropdownOpen by remember {
        mutableStateOf(false)
    }
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
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Type",
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        Column {
                            OutlinedButton(onClick = { typeDropdownOpen = true }) {
                                Text(text = categoryType.toString())
                            }
                            DropdownMenu(
                                expanded = typeDropdownOpen,
                                onDismissRequest = { typeDropdownOpen = false }) {
                                categoryTypes.forEach { categoryType ->
                                    DropdownMenuItem(text = {
                                        Text(text = categoryType.toString())
                                    }, onClick = {
                                        onChangeCategoryType(categoryType)
                                        typeDropdownOpen = false
                                    })
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Name",
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        OutlinedTextField(
                            value = categoryName,
                            onValueChange = onChangeCategoryName,
                            shape = RoundedCornerShape(size = 4.dp)

                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Icon",
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        OutlinedIconButton(onClick = onClickIconButton) {
                            Icon(
                                painter = painterResource(id = currentIcon.resourceId),
                                contentDescription = currentIcon.iconName
                            )
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
                        Button(onClick = onConfirmButtonClick) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditCategoryScreenPreview() {
    EditCategoryScreenContent()
}