package dev.pontakorn.habitbudget.ui.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pontakorn.habitbudget.data.Streak
import dev.pontakorn.habitbudget.data.StreakRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitTrackingViewModel @Inject constructor(
    private val streakRepository: StreakRepository
): ViewModel() {
    private val _streaks = MutableStateFlow<List<Streak>>(emptyList())
    val streaks = _streaks.asStateFlow()

    init {
        viewModelScope.launch {
            streakRepository.getAll().collect {
                _streaks.value = it
            }
        }
    }

}