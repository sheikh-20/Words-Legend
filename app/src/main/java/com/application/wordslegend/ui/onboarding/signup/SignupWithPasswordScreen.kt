package com.application.wordslegend.ui.onboarding.signup

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
fun SignupWithPasswordScreen(modifier: Modifier = Modifier,
                             paddingValues: PaddingValues = PaddingValues(),
                             onSignUpClick: (String?, String?) -> Unit = { _, _ ->},
                             onGoogleSignInClick: (Activity?, Intent?) -> Unit = { _, _ ->},
                             onSocialSignIn: SharedFlow<Resource<AuthResult>>? = null,
                             onAccountCreate: () -> Unit = {  },
                             email: String = "",
                             onEmailChange: (String) -> Unit = { _ -> },
                             password: String = "",
                             onPasswordChange: (String) -> Unit = { _ -> }) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Timber.tag("LoginScreen").d("OK -> ${result.data.toString()}")

//                onGoogleSignInClick(context as Activity, result.data)
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

                    onAccountCreate()

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
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Create an account ✏\uFE0F",
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold)

            Text(text = "Please enter your username, email address and password. If you forget it, then you have to do forget password",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth())

            Column {
//                Text(text = "Email")

                EmailComponent(focusManager = focusManager, email = email, onEmailUpdate = onEmailChange)
            }


            Column {
//                Text(text = "Password")

                PasswordComponent(focusManager = focusManager, password = password, onPasswordUpdate = onPasswordChange)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {})
                Text(text = "Remember me")
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

            Button(
                onClick = { onSignUpClick(email, password) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(50)
                    )) {

                Text(text = "Sign up")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignupWithPasswordLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        SignupWithPasswordScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SignupWithPasswordDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        SignupWithPasswordScreen()
    }
}