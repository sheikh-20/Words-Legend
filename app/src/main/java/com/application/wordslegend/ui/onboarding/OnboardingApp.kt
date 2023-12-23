package com.application.wordslegend.ui.onboarding

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.domain.model.Country
import com.application.wordslegend.ui.home.HomeActivity
import com.application.wordslegend.ui.onboarding.login.CreateNewPasswordScreen
import com.application.wordslegend.ui.onboarding.login.ForgotPasswordScreen
import com.application.wordslegend.ui.onboarding.login.LoginWithPasswordScreen
import com.application.wordslegend.ui.onboarding.login.OtpCodeScreen
import com.application.wordslegend.ui.onboarding.signup.SignupWithPasswordScreen
import com.application.wordslegend.ui.onboarding.signup.SignupWithPersonalDetailsScreen
import com.application.wordslegend.ui.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingApp(modifier: Modifier = Modifier,
                  navController: NavHostController = rememberNavController(),
                  onboardingViewModel: OnboardingViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()

    val onSocialSignIn = onboardingViewModel.socialSignIn

    var showBottomSheet by remember { mutableStateOf(false) }

    val countryUIState by onboardingViewModel.countryUIState.collectAsState()

    if (showBottomSheet) {
        BottomSheet(
            onDismiss = { showBottomSheet = !showBottomSheet },
            onPositiveClick = {
                showBottomSheet = !showBottomSheet
                onboardingViewModel.onCountryChange(it)
                              },
            countryUIState = countryUIState
        )
    }

    Scaffold(
        topBar = {
            OnboardingAppBar(currentScreen = backStackEntry?.destination?.route ?: OnboardingScreen.Start.name, canNavigateBack = navController.previousBackStackEntry != null) {
                navController.navigateUp()
            }
        },
    ){ paddingValues ->
        NavHost(modifier = modifier.padding(paddingValues), navController = navController, startDestination = OnboardingScreen.Start.name) {
            composable(route = OnboardingScreen.Start.name) {
                OnboardingScreen(
                    onSignupClick = {
                        navController.navigate(OnboardingScreen.SignupWithPersonal.name)
                    },
                    onLoginClick = {
                        navController.navigate(OnboardingScreen.LoginWithPassword.name)
                    }
                )
            }

            composable(route = OnboardingScreen.LoginWithPassword.name) {
                LoginWithPasswordScreen(
                    onForgotPasswordClick = {
                        navController.navigate(OnboardingScreen.ForgotPassword.name)
                    },
                    onSignInClick = { email: String?, password: String? -> onboardingViewModel.signInEmail(email, password) },
                    onGoogleSignInClick = { activity, intent ->  onboardingViewModel.signInGoogle(activity, intent) },
                    onSocialSignIn = onSocialSignIn,
                )
            }

            composable(route = OnboardingScreen.SignupWithPersonal.name) {
                SignupWithPersonalDetailsScreen(
                    onContinueClick = {
                        navController.navigate(OnboardingScreen.SignupWithPassword.name)
                    },
                    fullName = onboardingViewModel.fullName,
                    onFullNameChange = onboardingViewModel::onFullNameChange,
                    dob = onboardingViewModel.dob,
                    onDobChange = onboardingViewModel::onDonChange,
                    phoneNumber = onboardingViewModel.phoneNumber,
                    onPhoneNumberChange = onboardingViewModel::onPhoneNumberChange,
                    country = onboardingViewModel.country,
                    onCountryChange = { showBottomSheet = !showBottomSheet }
                )
            }

            composable(route = OnboardingScreen.SignupWithPassword.name) {
                SignupWithPasswordScreen(
                    paddingValues = paddingValues,
                    onSignUpClick = { email: String?, password: String? -> onboardingViewModel.signUpEmail(email, password) },
                    onSocialSignIn = onSocialSignIn,
                    onAccountCreate = onboardingViewModel::updateProfile,
                    email = onboardingViewModel.email,
                    onEmailChange = onboardingViewModel::onEmailChange,
                    password = onboardingViewModel.password,
                    onPasswordChange = onboardingViewModel::onPasswordChange,
                    onGoogleSignInClick = { activity, intent ->  onboardingViewModel.signInGoogle(activity, intent) },
                )
            }

            composable(route = OnboardingScreen.ForgotPassword.name) {
                ForgotPasswordScreen(
                    onContinueClick = {
                        navController.navigate(OnboardingScreen.OTPCode.name)
                    }
                )
            }

            composable(route = OnboardingScreen.OTPCode.name) {
                OtpCodeScreen(
                    onConfirmClick = {
                        navController.navigate(OnboardingScreen.CreateNewPassword.name)
                    }
                )
            }

            composable(route = OnboardingScreen.CreateNewPassword.name) {
                CreateNewPasswordScreen(

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet(modifier: Modifier = Modifier,
                        onDismiss: () -> Unit = {},
                        onPositiveClick: (String) -> Unit = { _ -> },
                        countryUIState: Resource<Country> = Resource.Loading
) {

    val bottomSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            coroutineScope.launch {
                onDismiss()
                bottomSheet.hide()
            }
        },
        sheetState = bottomSheet,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {

        Column(modifier = modifier
            .padding(16.dp)
            .systemBarsPadding(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "Country",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)

            Divider()

            when (countryUIState) {
                is Resource.Loading -> {

                }
                is Resource.Failure -> { }
                is Resource.Success -> {
                    LazyColumn {
                        items(countryUIState.data.country?.size ?: return@LazyColumn) { index ->
                            TextButton(onClick = { onPositiveClick(countryUIState.data.country[index]) }) {
                                Text(text = countryUIState.data.country[index], modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingAppBar(currentScreen: String, canNavigateBack: Boolean, onNavigateUp: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

enum class OnboardingScreen {
    Start,
    LoginWithPassword,
    SignupWithPersonal,
    SignupWithPassword,
    ForgotPassword,
    OTPCode,
    CreateNewPassword
}