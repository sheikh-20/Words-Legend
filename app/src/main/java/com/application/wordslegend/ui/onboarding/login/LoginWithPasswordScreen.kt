package com.application.wordslegend.ui.onboarding.login

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.R
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.domain.usecase.SignInGoogleInteractor
import com.application.wordslegend.ui.home.HomeActivity
import com.application.wordslegend.ui.onboarding.component.EmailComponent
import com.application.wordslegend.ui.onboarding.component.LoginComponent
import com.application.wordslegend.ui.onboarding.component.PasswordComponent
import com.application.wordslegend.ui.onboarding.component.SocialLoginComponent
import com.application.wordslegend.ui.theme.WordsLegendTheme
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoginWithPasswordScreen(modifier: Modifier = Modifier,
                            onForgotPasswordClick: () -> Unit = { },
                            onSignInClick: (String?, String?) -> Unit = { _, _ ->},
                            onGoogleSignInClick: (Activity?, Intent?) -> Unit = { _, _ ->},
                            onSocialSignIn: SharedFlow<Resource<AuthResult>>? = null) {

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Timber.tag("LoginScreen").d("OK -> ${result.data.toString()}")

                onGoogleSignInClick(context as Activity, result.data)
            } else {
                Timber.tag("LoginScreen").e("ERROR")
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        onSocialSignIn?.collectLatest {
            when(it) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Failure -> {
                    isLoading = false
                    if (it.throwable is FirebaseAuthInvalidUserException) {
//                        snackbarHostState.showSnackbar(message = "Email does not exists, Try signup!")
                    } else {
//                        snackbarHostState.showSnackbar(message = "Failure!")
                        Timber.tag("Login").e(it.throwable)
                    }
                }
                is Resource.Success -> {
                    isLoading = false
                    Timber.tag("Login").d("Google Success")

//                    if (it.data.additionalUserInfo?.isNewUser == true) {
//                        (context as Activity).finish()
//                        AccountSetupActivity.startActivity(context as Activity)
//                    } else {
                        (context as Activity).finish()
                        HomeActivity.startActivity((context as Activity))
//                    }
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Hello there \uD83D\uDC4B",
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold)

            Column {
//                Text(text = "Email")

                EmailComponent(
                    email = email,
                    onEmailUpdate = { email = it },
                    focusManager = focusManager)
            }

            Column {
//                Text(text = "Password")


                PasswordComponent(
                    password = password,
                    onPasswordUpdate = { password = it },
                    focusManager = focusManager
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {})
                Text(text = "Remember me")
            }

            Divider()

            TextButton(onClick = onForgotPasswordClick, modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)) {
                Text(text = "Forgot password?", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Divider(modifier = modifier.weight(1f), color = Color.LightGray)
                Text(text = "or")
                Divider(modifier = modifier.weight(1f), color = Color.LightGray)
            }


            Row(modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                SocialLoginComponent(
                    icon = R.drawable.ic_facebook,
                    onClick = {   })

                SocialLoginComponent(
                    icon = R.drawable.ic_google,
                    onClick = {
                        coroutineScope.launch {
                            val result = SignInGoogleInteractor.signIn(context)
                            launcher.launch(IntentSenderRequest.Builder(result ?: return@launch).build())
                        }
                    })
            }
        }

        Column(modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Divider(modifier = modifier.fillMaxWidth())

            Button(onClick = { onSignInClick(email, password) },
                modifier =  modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(50)
                    )) {

                if (isLoading) {
                    CircularProgressIndicator(modifier = modifier.size(30.dp), strokeWidth = 2.dp, trackColor = Color.White)
                } else {
                    Text(text = "Sign In")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginWithPasswordLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        LoginWithPasswordScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginWithPasswordDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        LoginWithPasswordScreen()
    }
}