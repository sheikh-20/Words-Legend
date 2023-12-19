package com.application.wordslegend.ui.onboarding

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.application.wordslegend.ui.home.HomeActivity
import com.application.wordslegend.ui.onboarding.login.CreateNewPasswordScreen
import com.application.wordslegend.ui.onboarding.login.ForgotPasswordScreen
import com.application.wordslegend.ui.onboarding.login.LoginScreen
import com.application.wordslegend.ui.onboarding.login.LoginWithPasswordScreen
import com.application.wordslegend.ui.onboarding.login.OtpCodeScreen
import com.application.wordslegend.ui.onboarding.signup.SignupWithPasswordScreen
import com.application.wordslegend.ui.onboarding.signup.SignupWithPersonalDetailsScreen
import com.application.wordslegend.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingApp(modifier: Modifier = Modifier,
                  navController: NavHostController = rememberNavController(),
                  onboardingViewModel: OnboardingViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()

    val onSocialSignIn = onboardingViewModel.socialSignIn

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
                    }
                )
            }

            composable(route = OnboardingScreen.SignupWithPassword.name) {
                SignupWithPasswordScreen(
                    paddingValues = paddingValues,
                    onSignupClick = {
                        HomeActivity.startActivity(context as Activity)
                    }
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
fun OnboardingAppBar(currentScreen: String, canNavigateBack: Boolean, onNavigateUp: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary)
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