package com.application.wordslegend.ui.onboarding.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.R
import com.application.wordslegend.ui.onboarding.component.SocialLoginComponent
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun ForgotPasswordScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit = { }) {

    var email by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(
                text = "Forgot Password \uD83D\uDD11",
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Enter your email address to get an OTP code to reset your password",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = modifier.fillMaxWidth()
            )

            Column {
//                Text(text = "Email")

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = Color.LightGray)
                )
            }
        }

        Column(modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Divider(modifier = modifier.fillMaxWidth())

            Button(
                onClick = onContinueClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(50)
                    )) {

                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgotPasswordLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        ForgotPasswordScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ForgotPasswordDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        ForgotPasswordScreen()
    }
}