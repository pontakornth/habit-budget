package dev.pontakorn.habitbudget.ui.habit

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.time.ZoneId


@Composable
fun HabitTrackingScreen(
    viewModel: HabitTrackingViewModel = hiltViewModel()
) {
    val streaks = viewModel.streaks.collectAsState()
    val streakLength = viewModel.streakLength.collectAsState()
    val hasStreak = viewModel.hasStreak.collectAsState()
    HabitTrackingScreenContent(
        streakText = "${if (hasStreak.value) streakLength.value else 0} day(s) streak",
        showNoTransactionButton = !hasStreak.value,
        streaks = streaks.value.map { it.streakDate.time },
        onNoTransactionClick = {
            Log.i("HabitTrackingScreen", "onNoTransactionClick")
        }
    )
}

@Composable
fun HabitTrackingScreenContent(
    streakText: String = "",
    showNoTransactionButton: Boolean = true,
    streaks: List<Long> = emptyList(),
    onNoTransactionClick: () -> Unit = {}
) {
    HabitBudgetTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Habit Tracking",
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                // TODO: Streak logic
                StaticCalendar(
                    dayContent = { day ->
                        val pureDate =
                            day.date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000
                        val color = if (streaks.contains(pureDate)) {
                            Color.Green
                        } else Color.White
                        Card(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .padding(2.dp),
//                            elevation = if (day.isFromCurrentMonth) 4.dp else 0.dp,
//                            border = if (day.isCurrentDay) BorderStroke(2.dp, color) else null,
                            colors = CardDefaults.cardColors(
                                containerColor = color
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = day.date.dayOfMonth.toString(),
                                    fontWeight = if (day.isCurrentDay) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                )
                Text(
                    modifier = Modifier.padding(vertical = 24.dp),
                    text = streakText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                if (showNoTransactionButton) {
                    Button(onClick = onNoTransactionClick) {
                        Text(text = "I don't use money today.")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitTrackingScreenPreview() {
    HabitTrackingScreenContent()
}