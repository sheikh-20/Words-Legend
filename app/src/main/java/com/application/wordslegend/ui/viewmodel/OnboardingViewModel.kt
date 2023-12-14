package com.application.wordslegend.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel: ViewModel() {

    private var _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> get() = _loading

    init {
        viewModelScope.launch {
            delay(3_000L)
            _loading.value = false
        }
    }
}