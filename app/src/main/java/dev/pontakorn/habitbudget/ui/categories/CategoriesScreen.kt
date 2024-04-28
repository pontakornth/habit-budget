package dev.pontakorn.habitbudget.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun CategoriesScreen() {
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                   Button(onClick = { /*TODO*/ }) {
                       Text(text = "Income")
                   }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Expense")
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