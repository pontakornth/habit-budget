package dev.pontakorn.habitbudget.ui.wallets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import dev.pontakorn.habitbudget.ui.icons.IconInfo
import dev.pontakorn.habitbudget.ui.icons.allIcons
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme

@Composable
fun EditWalletScreen(viewModel: ViewModel) {
}

@Composable
fun EditWalletScreenContent(
    title: String = "Add Wallet",
    walletName: String = "",
    onChangeWalletName: (String) -> Unit = {},
    currentIcon: IconInfo = allIcons[0],
    onClickIconButton: () -> Unit = {},
    onBackButtonClick: () -> Unit = {},
    onConfirmButtonClick: () -> Unit = {}
) {
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Name",
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        OutlinedTextField(
                            value = walletName,
                            onValueChange = onChangeWalletName,
                            shape = RoundedCornerShape(size = 4.dp)

                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Icon",
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        OutlinedIconButton(onClick = onClickIconButton) {
                            Icon(
                                painter = painterResource(id = currentIcon.resourceId),
                                contentDescription = currentIcon.iconName
                            )
                        }


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 32.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(onClick = onBackButtonClick) {
                            Text(text = "Back")
                        }
                        Button(onClick = onConfirmButtonClick) {
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
fun EditCategoryScreenPreview() {
    EditWalletScreenContent()
}