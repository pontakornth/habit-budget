package dev.pontakorn.habitbudget.ui.transaction

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import java.util.Calendar
import java.util.Date

@Composable
fun TransactionItem(
    transactionTitle: String,
    transactionAmount: Double,
    transactionIcon: ImageVector,
    transactionDate: Date
) {
    HabitBudgetTheme {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = transactionIcon, contentDescription = transactionTitle)
                // TODO: Add wallet icon
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = transactionTitle)
                    Text(text = transactionTitle)
                }

                Text(text = transactionAmount.toString())
            }

        }
    }
}

@Preview
@Composable
fun TransactionItemPreview() {
    TransactionItem(
        transactionTitle = "Example transaction",
        transactionAmount = 99.99,
        transactionIcon = Icons.Outlined.Star,
        transactionDate = Date(1713799047226)
    )
}