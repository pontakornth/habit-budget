package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun WalletItemView(
    walletIcon: ImageVector,
    walletName: String,
    walletDisplayAmount: String,
    onClickWalletItem: () -> Unit = {}
) {
    HabitBudgetTheme {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onClickWalletItem()
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.border(BorderStroke(2.dp, Color.Black))
                ) {
                    Icon(
                        imageVector = walletIcon,
                        contentDescription = walletName,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(text = walletName)
                Text(text = walletDisplayAmount, textAlign = TextAlign.End)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletItemViewPreview() {
    WalletItemView(
        walletIcon = Icons.Outlined.Star,
        walletName = "Bank",
        walletDisplayAmount = "2,000"
    )
}