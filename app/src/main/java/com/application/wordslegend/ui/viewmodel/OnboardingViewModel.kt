package com.application.wordslegend.ui.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.domain.model.Country
import com.application.wordslegend.domain.model.Member
import com.application.wordslegend.domain.usecase.AccountSetupUseCase
import com.application.wordslegend.domain.usecase.CountryUseCase
import com.application.wordslegend.domain.usecase.SignInEmailUseCase
import com.application.wordslegend.domain.usecase.SignInGoogleUseCase
import com.application.wordslegend.domain.usecase.SignUpEmailUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val signInGoogleUseCase: SignInGoogleUseCase,
                                              private val signInEmailUseCase: SignInEmailUseCase,
                                              private val countryUseCase: CountryUseCase,
                                              private val signUpEmailUseCase: SignUpEmailUseCase,
                                              private val accountSetupUseCase: AccountSetupUseCase): ViewModel() {

    companion object {
        private const val TAG = "OnboardingViewModel"
    }


    private val auth = Firebase.auth


    private var _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> get() = _loading

    private var _socialSignIn = MutableSharedFlow<Resource<AuthResult>>()
    val socialSignIn get() = _socialSignIn.asSharedFlow()

    private var _countryUIState = MutableStateFlow<Resource<Country>>(Resource.Loading)
    val countryUIState: StateFlow<Resource<Country>> get() = _countryUIState

    var fullName by mutableStateOf("")
        private set

    var dob by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var country by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

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

    fun signUpEmail(email: String?, password: String?) = viewModelScope.launch {
        signUpEmailUseCase(email = email, password = password).collectLatest {
            Timber.tag(TAG).d("Email called")
            _socialSignIn.emit(it)
        }
    }

    fun onFullNameChange(value: String) {
        fullName = value
    }

    fun onDonChange(value: String) {
        dob = value
    }

    fun onPhoneNumberChange(value: String) {
        phoneNumber = value
    }

    fun onCountryChange(value: String) {
        country = value
    }

    fun onUsernameChange(value: String) {
        username = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    private fun getCountry() = viewModelScope.launch {
        countryUseCase().collectLatest {
            _countryUIState.value = it
        }
    }

    fun updateProfile() = viewModelScope.launch(Dispatchers.IO) {
        try {
            accountSetupUseCase.updateInfo(auth.currentUser?.uid ?: return@launch, Member(fullName, dob, phoneNumber, country, email))
        } catch (exception: IOException) {
            Timber.tag(TAG).e(exception)
        }
    }

    init {
        viewModelScope.launch {
            delay(3_000L)
            _loading.value = false
        }
        getCountry()
    }
}