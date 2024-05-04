package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import java.util.Date

// TODO: Make it re-usable
// Edit transaction should do the similar thing.

abstract class EditTransactionViewModel : ViewModel() {

}

@Composable
fun EditTransactionScreen(
    navController: NavController,
    viewModel: EditTransactionViewModel,
    title: String
) {
    // TODO: Use viewModel
    EditTransactionScreenContent(
        title = title,
        onBack = { navController.popBackStack() }
    )
}

@Composable
fun EditTransactionScreenContent(
    title: String = "Add Transaction",
    transactionType: TransactionType = TransactionType.INCOME,
    sourceWallet: Wallet? = null,
    onChangeSourceWallet: (Wallet) -> Unit = {},
    destinationWallet: Wallet? = null,
    onChangeDestinationWallet: (Wallet) -> Unit = {},
    // Note: amount is in baht but saved in satang.
    amount: Double = 0.0,
    onChangeAmount: (Double) -> Unit = {},
    // Should be the current date
    transactionDate: Date? = null,
    onChangeTransactionDate: (Date) -> Unit = {},
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Add Transaction",
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        lineBreak = LineBreak.Heading
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Type", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(size = 4.dp)
                        ) {
                            Text(text = "Expense", textAlign = TextAlign.End)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Wallet", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(size = 4.dp)
                        ) {
                            Text(text = "Bank Account", textAlign = TextAlign.End)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Category", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(size = 4.dp)
                        ) {
                            Text(text = "Bakery", textAlign = TextAlign.End)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Amount", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        OutlinedTextField(
                            value = "",
                            onValueChange = { TODO() },
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Date", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(size = 4.dp)
                        ) {
                            Text(text = "Date", textAlign = TextAlign.End)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 32.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(onClick = onBack) {
                            Text(text = "Back")
                        }
                        Button(onClick = onConfirm) {
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
fun EditTransactionScreenPreview() {
    EditTransactionScreenContent()
}