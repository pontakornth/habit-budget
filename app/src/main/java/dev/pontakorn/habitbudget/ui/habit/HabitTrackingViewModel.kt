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
    private val _streakLength = MutableStateFlow<Int>(0)
    val streakLength = _streakLength.asStateFlow()
//    private val _hasStreak = MutableStateFlow<Boolean>(false)
//    val hasStreak = _hasStreak.asStateFlow()
//    private val _hasStreakYesterday = MutableStateFlow<Boolean>(false)
//    val hasStreakYesterday = _hasStreakYesterday.asStateFlow()

    private val _hasStreak = MutableStateFlow<Boolean>(false)
    val hasStreak = _hasStreak.asStateFlow()

    fun onNoTransactionButtonClicked() {
        viewModelScope.launch {
            streakRepository.insertForToday()
        }
    }

    init {
        viewModelScope.launch {
            streakRepository.getAll().collect {
                _streaks.value = it
            }
        }
        viewModelScope.launch {
            streakRepository.getStreakLength().collect {
                _streakLength.value = it
            }
        }
        viewModelScope.launch {
            streakRepository.streaksExistTodayFlow().collect {
                if (!_hasStreak.value) {
                    _hasStreak.value = it
                }
            }
        }
        viewModelScope.launch {
            streakRepository.streaksExistYesterdayFlow().collect {
                if (!_hasStreak.value) {
                    _hasStreak.value = it
                }
            }
        }
    }

}