package com.application.wordslegend.ui.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.domain.usecase.SignInEmailUseCase
import com.application.wordslegend.domain.usecase.SignInGoogleUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val signInGoogleUseCase: SignInGoogleUseCase,
                                              private val signInEmailUseCase: SignInEmailUseCase): ViewModel() {

    companion object {
        private const val TAG = "OnboardingViewModel"
    }

    private var _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> get() = _loading


    private var _socialSignIn = MutableSharedFlow<Resource<AuthResult>>()
    val socialSignIn get() = _socialSignIn.asSharedFlow()


    fun signInGoogle(activity: Activity?, intent: Intent?) = viewModelScope.launch {
        signInGoogleUseCase(activity, intent).collectLatest {
            Timber.tag(TAG).d("Google called")
            _socialSignIn.emit(it)
        }
    }

    fun signInEmail(email: String?, password: String?) = viewModelScope.launch {
        signInEmailUseCase(email = email, password = password).collectLatest {
            Timber.tag(TAG).d("Email called")
            _socialSignIn.emit(it)
        }
    }

    init {
        viewModelScope.launch {
            delay(3_000L)
            _loading.value = false
        }
    }
}