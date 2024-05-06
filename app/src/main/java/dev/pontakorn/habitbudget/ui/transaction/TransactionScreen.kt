package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.ui.icons.allIcons
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import java.util.Date

@Composable
fun TransactionScreen(
    income: Double,
    expense: Double,
    remaining: Double,
    transactions: List<TransactionDisplayItem> = listOf(),
    onClickTransaction: (TransactionDisplayItem) -> Unit = {},
    onClickMonthSelector: () -> Unit = {},
    onChangeMonth: (String) -> Unit = {},
) {


    // TODO: Use actual earliest date.
    val months = listOf("Jan 2024", "Feb 2024", "Mar 2024")
    var monthPickerExpanded by remember {
        mutableStateOf(false)
    }
    var selectedMonth by remember {
        mutableStateOf("Jan 2024")
    }
    // TODO: Use transactions
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Transactions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        horizontalAlignment = Alignment.End

                    ) {
                        Text(text = "Income", textAlign = TextAlign.End)
                        Text(text = income.toString(), textAlign = TextAlign.End)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Expense", textAlign = TextAlign.End)
                        Text(text = expense.toString(), textAlign = TextAlign.End)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Remaining", textAlign = TextAlign.End)
                        Text(text = remaining.toString(), textAlign = TextAlign.End)
                    }
                }
                if (transactions.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color.Gray, text = "No transaction"
                    )
                }
                Column {
                    OutlinedButton(onClick = { monthPickerExpanded = true }) {
                        Text(text = selectedMonth)
                    }
                    DropdownMenu(
                        expanded = monthPickerExpanded,
                        onDismissRequest = { monthPickerExpanded = false }) {
                        months.forEach { choice ->
                            DropdownMenuItem(text = { Text(text = choice) }, onClick = {
                                selectedMonth = choice
                                monthPickerExpanded = false
                            })
                        }
                    }
                }
                // TODO: Use actual lazy columns or something
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 0.dp)
                ) {
                    for (transaction in transactions) {
                        TransactionItemView(
                            transactionTitle = transaction.transactionTitle,
                            transactionAmount = transaction.transactionAmount,
                            transactionIcon = transaction.transactionIcon,
                            transactionDate = transaction.transactionDate,
                            transactionSourceWalletIcon = transaction.transactionSourceWalletIcon
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(
        income = 9999.0,
        expense = 9999.0,
        remaining = 0.0
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreviewWithTransaction() {
    TransactionScreen(
        income = 9999.0,
        expense = 9999.0,
        remaining = 0.0,
        transactions = listOf(
            TransactionDisplayItem(
                transactionTitle = "Food",
                transactionAmount = 99.0,
                transactionDate = Date(),
                transactionIcon = allIcons[0],
                transactionSourceWalletIcon = allIcons[0]
            ),
            TransactionDisplayItem(
                transactionTitle = "Water",
                transactionAmount = 99.0,
                transactionDate = Date(),
                transactionIcon = allIcons[0],
                transactionSourceWalletIcon = allIcons[0]
            ),

        )
    )
}