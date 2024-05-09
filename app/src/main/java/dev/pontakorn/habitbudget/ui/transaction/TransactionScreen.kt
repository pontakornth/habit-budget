package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    transactions: List<TransactionDisplayItem>,
    onClickTransaction: (TransactionDisplayItem) -> Unit = {},
) {


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
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 0.dp)
                ) {
                    items(transactions) {transaction ->
                        TransactionItemView(
                            transactionTitle = "${transaction.transactionTitle} - ${transaction.id}",
                            transactionAmount = transaction.transactionAmount,
                            transactionIcon = transaction.transactionIcon,
                            transactionDate = transaction.transactionDate,
                            transactionSourceWalletIcon = transaction.transactionSourceWalletIcon,
                            onClick = {
                                onClickTransaction(transaction)
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
fun TransactionScreenPreview() {
    TransactionScreen(
        income = 9999.0,
        expense = 9999.0,
        remaining = 0.0,
        transactions = emptyList()
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