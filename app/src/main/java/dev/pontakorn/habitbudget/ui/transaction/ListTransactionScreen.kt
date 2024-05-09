package dev.pontakorn.habitbudget.ui.transaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun ListTransactionScreen(
    navController: NavController,
    viewModel: ListTransactionViewModel = hiltViewModel()
) {
    val transactionSummary = viewModel.transactionSummary.collectAsState()
    val transactionList = viewModel.transactionList.collectAsState()
    TransactionScreen(
        income = transactionSummary.value?.income?.div(100.0) ?: 0.0,
        expense = transactionSummary.value?.expense?.div(100.0) ?: 0.0,
        remaining = transactionSummary.value?.remaining?.div(100.0) ?: 0.0,
        transactions = transactionList.value,
        onClickTransaction = {
            navController.navigate("transactions/${it.id}/update")
        },
        monthRange = viewModel.monthRange,
    )
}