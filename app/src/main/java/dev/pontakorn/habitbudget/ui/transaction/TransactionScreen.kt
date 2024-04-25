package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
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
import dev.pontakorn.habitbudget.data.Transaction
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun TransactionScreen(
    transactions: List<Transaction> = listOf()
) {
    // TODO: Use transactions
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
                // TODO: Use actual lazy columns or something
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
                        Text(text = "99999", textAlign = TextAlign.End)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Expense", textAlign = TextAlign.End)
                        Text(text = "99999", textAlign = TextAlign.End)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Remaining", textAlign = TextAlign.End)
                        Text(text = "99999", textAlign = TextAlign.End)
                    }
                }
                if (transactions.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color.Gray, text = "No transaction")
                }
                for (transaction in transactions) {
                    TransactionItem(
                        // TODO: Use transaction title
                        transactionTitle = "Placeholder",
                        // TODO: Convert satang to baht
                        transactionAmount = transaction.amount.toDouble(),
                        // TODO: Use actual icon
                        transactionIcon = Icons.Outlined.Star,
                        transactionDate = transaction.transactionTime
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen()
}