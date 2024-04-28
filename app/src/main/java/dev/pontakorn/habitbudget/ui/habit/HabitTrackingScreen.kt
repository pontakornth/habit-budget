package dev.pontakorn.habitbudget.ui.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pontakorn.habitbudget.DestinationScreens
import dev.pontakorn.habitbudget.ui.theme.HabitBudgetTheme
import io.github.boguszpawlowski.composecalendar.StaticCalendar


@Composable
fun HabitTracking() {
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
                StaticCalendar()
                Text(
                    modifier = Modifier.padding(vertical = 24.dp),
                    text = "4 days streak",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "I don't use money today.")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitTrackingScreenPreview() {
    HabitTracking()
}