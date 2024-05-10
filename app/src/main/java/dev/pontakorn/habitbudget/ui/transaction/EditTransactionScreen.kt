@file:OptIn(ExperimentalMaterial3Api::class)

package dev.pontakorn.habitbudget.ui.transaction

import android.util.Log
import android.widget.TimePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import dev.pontakorn.habitbudget.data.Category
import dev.pontakorn.habitbudget.data.CategoryType
import dev.pontakorn.habitbudget.data.TransactionType
import dev.pontakorn.habitbudget.data.Wallet
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import dev.pontakorn.habitbudget.utils.DateUtil.getFormattedDate
import dev.pontakorn.habitbudget.utils.DecimalFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTransactionScreen(
    navController: NavController,
    viewModel: EditTransactionViewModel,
    title: String
) {
    val uiState = viewModel.uiState.collectAsState()
    val walletIdFromNavController =
        navController.currentBackStackEntry?.savedStateHandle?.get<Int>("wallet_id")
    val modeFromNavController =
        navController.currentBackStackEntry?.savedStateHandle?.get<Int>("mode")
    val categoryIdFromNavController =
        navController.currentBackStackEntry?.savedStateHandle?.get<Int>("category_id")
    LaunchedEffect(key1 = walletIdFromNavController, key2 = modeFromNavController) {
        if (modeFromNavController == 1) {
            walletIdFromNavController?.run {
                viewModel.getSourceWallet(walletIdFromNavController)
            }
        } else if (modeFromNavController == 2) {
            walletIdFromNavController?.run {
                viewModel.getDestinationWallet(walletIdFromNavController)
            }
        }
    }
    LaunchedEffect(key1 = categoryIdFromNavController) {
        viewModel.getCategory(categoryIdFromNavController)
    }
    var datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = uiState.value.transactionDate.time)
    LaunchedEffect(key1 = datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            viewModel.setTransactionDate(Date(it))
        }
    }
    // Set initial
    LaunchedEffect(true) {
        // Black magic by https://stackoverflow.com/questions/2873119/changing-timezone-without-changing-time-in-java

        // transactionDate is LOCAL TIME
        val defaultCalendar = Calendar.getInstance(TimeZone.getDefault())
        defaultCalendar.timeInMillis = uiState.value.transactionDate.time
        val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        Log.i("EditTransactionScreen", "UTC Before: ${utcCalendar.timeInMillis}")
        utcCalendar.set(Calendar.YEAR, defaultCalendar.get(Calendar.YEAR))
        utcCalendar.set(Calendar.DAY_OF_YEAR, defaultCalendar.get(Calendar.DAY_OF_YEAR))
        utcCalendar.set(Calendar.HOUR_OF_DAY, defaultCalendar.get(Calendar.HOUR_OF_DAY))
        utcCalendar.set(Calendar.MINUTE, defaultCalendar.get(Calendar.MINUTE))
        utcCalendar.set(Calendar.SECOND, defaultCalendar.get(Calendar.SECOND))
        Log.i("EditTransactionScreen", "UTC After: ${utcCalendar.timeInMillis}")


        datePickerState.selectedDateMillis = utcCalendar.timeInMillis
    }
    var hourState by remember {
        mutableIntStateOf(uiState.value.transactionTime.first)
    }
    var minuteState by remember {
        mutableIntStateOf(uiState.value.transactionTime.second)
    }

    LaunchedEffect(
        key1 = uiState.value.transactionTime.first,
        key2 = uiState.value.transactionTime.second
    ) {
        hourState = uiState.value.transactionTime.first
        minuteState = uiState.value.transactionTime.second

    }
    EditTransactionScreenContent(
        title = title,
        onBack = { navController.popBackStack() },
        transactionType = uiState.value.transactionType,
        onChangeTransactionType = {
            if (uiState.value.transactionType != it) {
                viewModel.deleteCategory()
            }
            viewModel.setTransactionType(it)
        },
        sourceWallet = uiState.value.sourceWallet,
        onClickSourceWallet = {
            navController.navigate("wallets?selectMode=1")
        },
        destinationWallet = uiState.value.destinationWallet,
        onClickDestinationWallet = {
            navController.navigate("wallets?selectMode=2")
        },
        category = uiState.value.category,
        onClickCategory = {
            val categoryType =
                if (uiState.value.transactionType == TransactionType.EXPENSE) CategoryType.EXPENSE.ordinal else CategoryType.INCOME.ordinal
            navController.navigate("categories?shouldSelect=true&categoryType=$categoryType")
        },
        amountString = viewModel.amountString,
        onChangeAmountString = { viewModel.setNewAmountString(it) },
        datePickerState = datePickerState,
        hour = hourState,
        minute = minuteState,
        onTimeChange = { newHour, newMinute ->
            hourState = newHour
            minuteState = newMinute
            viewModel.setTransactionTime(hourState to minuteState)

        },
        onConfirm = {
            viewModel.onConfirm()
            navController.popBackStack()
        },
        allowConfirm = viewModel.allowAddTransaction()
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
    category: Category? = null,
    onClickCategory: () -> Unit = {},
    destinationWallet: Wallet? = null,
    onClickDestinationWallet: () -> Unit = {},
    // Note: amount is in baht but saved in satang.
    amountString: String = "0.0",
    onChangeAmountString: (String) -> Unit = {},
    datePickerState: DatePickerState,
    hour: Int = 0,
    minute: Int = 0,
    onTimeChange: (Int, Int) -> Unit = { _, _ -> {} },
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {},
    allowConfirm: Boolean = true,
) {

    var transactionTypeDropdownOpen by remember {
        mutableStateOf(false)
    }
//    var datePickerState = rememberDatePickerState(initialSelectedDateMillis = transactionDate?.time)
    var showDatePicker by remember { mutableStateOf(false) }

    var showTimePicker by remember { mutableStateOf(false) }
    // This allows reverting
    var localHour by remember { mutableStateOf(hour) }
    var localMinute by remember { mutableStateOf(minute) }
    val decimalFormatter = DecimalFormatter()
    val transactionTypes = listOf(
        TransactionType.EXPENSE,
        TransactionType.INCOME,
        TransactionType.TRANSFER
    )
    var timePicker: TimePicker
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
                    text = title,
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
                                Text(
                                    text = destinationWallet?.name ?: "Select wallet",
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                    }
                    if (transactionType != TransactionType.TRANSFER) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Category",
                                fontWeight = FontWeight.Medium,
                                fontSize = 24.sp
                            )
                            OutlinedButton(
                                onClick = onClickCategory,
                                shape = RoundedCornerShape(size = 4.dp)
                            ) {
                                Text(
                                    text = category?.name ?: "Choose category",
                                    textAlign = TextAlign.End
                                )
                            }
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
                            value = amountString,
                            onValueChange = {
                                onChangeAmountString(it)
//                                rawAmount = decimalFormatter.cleanup(it)
//                                onChangeAmount(it.ifEmpty { "0" }.toBigDecimal().toDouble())
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
                                    text = datePickerState.selectedDateMillis?.let {
                                        getFormattedDate(Date(it))
                                    } ?: "Select date",
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
                                Text(
                                    text = "$hour:$minute",
                                    textAlign = TextAlign.End
                                )
                            }
                            if (showTimePicker) {
                                Dialog(
                                    onDismissRequest = { showTimePicker = false },
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        AndroidView(factory = { context ->
                                            TimePicker(context).apply {
                                                // Set initial state again
                                                localHour = hour
                                                setHour(localHour)
                                                localMinute = minute
                                                setMinute(localMinute)
                                                setIs24HourView(true)
                                                setOnTimeChangedListener { _, hour, minute ->
                                                    localHour = hour
                                                    localMinute = minute
                                                }
                                            }
                                        })
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(32.dp),
                                            horizontalArrangement = Arrangement.End,
                                        ) {
                                            Button(onClick = {
                                                // Revert
                                                localHour = hour
                                                localMinute = minute
                                                showTimePicker = false
                                            }) {
                                                Text(text = "Dismiss")
                                            }
                                            Button(onClick = {
                                                // Commit
                                                onTimeChange(localHour, localMinute)
                                                showTimePicker = false
                                            }) {
                                                Text(text = "Confirm")
                                            }
                                        }
                                    }
                                }
//
//                                )
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
                        Button(enabled = allowConfirm, onClick = onConfirm) {
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
    EditTransactionScreenContent(
        datePickerState = rememberDatePickerState(),
        hour = 0,
        minute = 0
    )
}

@Preview(showBackground = true)
@Composable
fun EditTransactionScreenTransferPreview() {
    EditTransactionScreenContent(
        transactionType = TransactionType.TRANSFER,
        datePickerState = rememberDatePickerState(),
        hour = 0,
        minute = 0
    )
}