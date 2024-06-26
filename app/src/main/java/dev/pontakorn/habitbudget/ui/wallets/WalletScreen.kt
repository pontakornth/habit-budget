package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.data.WalletSummary
import dev.pontakorn.habitbudget.ui.icons.allIcons
import dev.pontakorn.habitbudget.ui.icons.findIcon
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun WalletScreen(
    wallets: List<Wallet> = emptyList(),
    walletSummaries: List<WalletSummary> = emptyList(),
    onClickWalletCard: (Int) -> Unit = {}
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
                    text = "Wallets",
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    userScrollEnabled = true
                ) {
                    items(wallets) { wallet ->
                        WalletItemView(
                            walletIcon = findIcon(wallet.iconName) ?: allIcons[0],
                            walletName = wallet.name,
                            walletDisplayAmount = walletSummaries.firstOrNull { wallet.id == it.walletId }?.balance?.div(
                                100.0
                            )?.toString() ?: "0.0",
                            onClickWalletItem = { onClickWalletCard(wallet.id) }
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
    WalletScreen()
}