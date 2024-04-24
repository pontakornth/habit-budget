package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                .padding(4.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Transactions")
                // TODO: Use actual lazy columns or something
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