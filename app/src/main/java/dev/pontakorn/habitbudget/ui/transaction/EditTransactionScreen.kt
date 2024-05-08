package dev.pontakorn.habitbudget.ui.transaction

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import dev.pontakorn.habitbudget.ui.utils.TimePickerDialog
import dev.pontakorn.habitbudget.utils.DateUtil.getFormattedDate
import dev.pontakorn.habitbudget.utils.DateUtil.getFormattedTime
import dev.pontakorn.habitbudget.utils.DecimalFormatter
import java.util.Date


@Composable
fun EditTransactionScreen(
    navController: NavController,
    viewModel: EditTransactionViewModel,
    title: String
) {
    val walletIdFromNavController = navController.currentBackStackEntry?.savedStateHandle?.get<Int>("wallet_id")
    val modeFromNavController = navController.currentBackStackEntry?.savedStateHandle?.get<Int>("mode")
    LaunchedEffect(key1 = walletIdFromNavController, key2 = modeFromNavController) {
        if (modeFromNavController == 1) {
            walletIdFromNavController?.run {
                // TODO: Get wallet by id
                viewModel.getSourceWallet(walletIdFromNavController)
                Log.i("EditTransactionScreen", "select wallet $this as source wallet")
            }
        } else if (modeFromNavController == 2) {
            walletIdFromNavController?.run {
                viewModel.getDestinationWallet(walletIdFromNavController)
                Log.i("EditTransactionScreen", "select wallet $this as destination wallet")
            }
        }
    }
    EditTransactionScreenContent(
        title = title,
        onBack = { navController.popBackStack() },
        transactionType = viewModel.transactionType,
        onChangeTransactionType = { viewModel.transactionType = it },
        sourceWallet = viewModel.sourceWallet,
        onClickSourceWallet = {
            navController.navigate("wallets?selectMode=1")
        },
        destinationWallet = viewModel.destinationWallet,
        onClickDestinationWallet = {
            navController.navigate("wallets?selectMode=2")
        },
        amount = viewModel.amount,
        onChangeAmount = { viewModel.amount = it },
        transactionDate = viewModel.transactionDate,
        onChangeTransactionDate = { viewModel.transactionDate = it },
        transactionTime = viewModel.transactionTime,
        onChangeTransactionTime = { viewModel.transactionTime = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTransactionScreenContent(
    title: String = "Add Transaction",
    transactionType: TransactionType = TransactionType.INCOME,
    onChangeTransactionType: (TransactionType) -> Unit = {},
    sourceWallet: Wallet? = null,
    onClickSourceWallet: () -> Unit = {},
    onChangeSourceWallet: (Wallet) -> Unit = {},
    destinationWallet: Wallet? = null,
    onClickDestinationWallet: () -> Unit = {},
    onChangeDestinationWallet: (Wallet) -> Unit = {},
    // Note: amount is in baht but saved in satang.
    amount: Double = 0.0,
    onChangeAmount: (Double) -> Unit = {},
    transactionDate: Date? = Date(),
    onChangeTransactionDate: (Date) -> Unit = {},
    transactionTime: Pair<Int, Int> = 0 to 0,
    onChangeTransactionTime: (Pair<Int, Int>) -> Unit = {},
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {

    // Raw
    var rawAmount by remember {
        // Note: This assumes the value is not abnormally high or low.
        mutableStateOf(amount.toString())
    }
    var transactionTypeDropdownOpen by remember {
        mutableStateOf(false)
    }
    // TODO: Add date range
    var datePickerState = rememberDatePickerState(initialSelectedDateMillis = transactionDate?.time)
    LaunchedEffect(key1 = datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            onChangeTransactionDate(Date(it))
        }
    }
    var showDatePicker by remember { mutableStateOf(false) }

    var timePickerState = rememberTimePickerState()
    LaunchedEffect(key1 = timePickerState.hour, key2 = timePickerState.minute) {

        Log.d(
            "EditTransactionScreenContent",
            "Hour: ${timePickerState.hour}, Minute: ${timePickerState.minute}"
        )
        onChangeTransactionTime(timePickerState.hour to timePickerState.minute)
    }
    var showTimePicker by remember { mutableStateOf(false) }
    val decimalFormatter = DecimalFormatter()
    val transactionTypes = listOf(
        TransactionType.EXPENSE,
        TransactionType.INCOME,
        TransactionType.TRANSFER
    )
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
                        Column {
                            OutlinedButton(
                                onClick = { transactionTypeDropdownOpen = true },
                                shape = RoundedCornerShape(size = 4.dp)
                            ) {
                                Text(text = transactionType.toString(), textAlign = TextAlign.End)
                            }
                            DropdownMenu(
                                expanded = transactionTypeDropdownOpen,
                                onDismissRequest = { transactionTypeDropdownOpen = false }) {
                                transactionTypes.forEach { transactionType ->
                                    DropdownMenuItem(
                                        text = { Text(text = transactionType.toString()) },
                                        onClick = {
                                            onChangeTransactionType(transactionType)
                                            transactionTypeDropdownOpen = false
                                        })
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = if (transactionType == TransactionType.TRANSFER) {
                                "From wallet"
                            } else "Wallet", fontWeight = FontWeight.Medium, fontSize = 24.sp
                        )
                        OutlinedButton(
                            onClick = onClickSourceWallet,
                            shape = RoundedCornerShape(size = 4.dp)
                        ) {
                            Text(
                                text = sourceWallet?.name ?: "Select wallet",
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    if (transactionType == TransactionType.TRANSFER) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "To wallet", fontWeight = FontWeight.Medium, fontSize = 24.sp
                            )
                            OutlinedButton(
                                onClick = onClickDestinationWallet,
                                shape = RoundedCornerShape(size = 4.dp)
                            ) {
                                Text(text = destinationWallet?.name ?: "Select wallet", textAlign = TextAlign.End)
                            }
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
                            value = rawAmount,
                            onValueChange = {
                                rawAmount = decimalFormatter.cleanup(it)
                                onChangeAmount(it.ifEmpty { "0" }.toBigDecimal().toDouble())
                            },
                            shape = RoundedCornerShape(size = 4.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Date", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        Column {
                            OutlinedButton(
                                onClick = { showDatePicker = true },
                                shape = RoundedCornerShape(size = 4.dp)
                            ) {
                                Text(
                                    text = getFormattedDate(transactionDate ?: Date()),
                                    textAlign = TextAlign.End
                                )
                            }
                            if (showDatePicker) {
                                DatePickerDialog(
                                    onDismissRequest = { showDatePicker = false },
                                    confirmButton = {
                                        Button(onClick = { showDatePicker = false }) {
                                            Text(text = "Confirm")
                                        }
                                    },
                                    dismissButton = {
                                        Button(onClick = { showDatePicker = false }) {
                                            Text(text = "Cancel")
                                        }
                                    }

                                ) {
                                    DatePicker(state = datePickerState)
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Time", fontWeight = FontWeight.Medium, fontSize = 24.sp)
                        Column {
                            OutlinedButton(
                                onClick = { showTimePicker = true },
                                shape = RoundedCornerShape(size = 4.dp)
                            ) {
                                // TODO: Use function to actually forma
                                Text(
                                    text = getFormattedTime(
                                        transactionTime.first,
                                        transactionTime.second
                                    ), textAlign = TextAlign.End
                                )
                            }
                            if (showTimePicker) {
                                TimePickerDialog(
                                    title = "Choose transaction time",
                                    onDismissRequest = { showTimePicker = false },
                                    confirmButton = {
                                        Button(onClick = { showTimePicker = false }) {
                                            Text(text = "Confirm")
                                        }
                                    },
                                    dismissButton = {
                                        Button(onClick = { showTimePicker = false }) {
                                            Text(text = "Cancel")
                                        }
                                    }

                                ) {
                                    TimePicker(state = timePickerState)
                                }
                            }
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

@Preview(showBackground = true)
@Composable
fun EditTransactionScreenTransferPreview() {
    EditTransactionScreenContent(
        transactionType = TransactionType.TRANSFER
    )
}