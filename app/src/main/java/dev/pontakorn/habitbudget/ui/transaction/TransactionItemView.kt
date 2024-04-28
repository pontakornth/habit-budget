package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun TransactionItemView(
    transactionTitle: String,
    transactionAmount: Double,
    transactionIcon: ImageVector,
    transactionDate: Date
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy")
    val localTransactionDate = LocalDateTime.ofInstant(transactionDate.toInstant(), ZoneId.systemDefault())
    HabitBudgetTheme {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.border(BorderStroke(2.dp, Color.Black))
                ) {
                    Icon(
                        imageVector = transactionIcon,
                        contentDescription = transactionTitle,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                // TODO: Add wallet icon
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = transactionTitle)
                    Text(text = localTransactionDate.format(formatter))
                }

                Text(text = transactionAmount.toString())
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    TransactionItemView(
        transactionTitle = "Example transaction",
        transactionAmount = 99.99,
        transactionIcon = Icons.Outlined.Star,
        transactionDate = Date()
    )
}