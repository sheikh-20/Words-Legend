package com.application.wordslegend.ui.onboarding.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun OtpCodeScreen(modifier: Modifier = Modifier, onConfirmClick: () -> Unit = {  }) {

    val focusManager = LocalFocusManager.current
    var smsCode by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(
                text = "You've got email",
                style = MaterialTheme.typography.displaySmall,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "We have sent the OTP verification code to your email address. Check your email and enter the code below",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = modifier.fillMaxWidth()
            )


            Row(modifier = modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = smsCode,
                    onValueChange = {
                        if (it.length <= 4) {
                            smsCode = it
                        }
                    },
                    modifier = modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    singleLine = true,
                    decorationBox = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            repeat(4) {
                                val char = when {
                                    it >= smsCode.length -> ""
                                    else -> smsCode[it].toString()
                                }

                                val isFocused = smsCode.length == it

                                isChecked = smsCode.length == 4

                                androidx.compose.material.Text(
                                    text = char,
                                    modifier = modifier
                                        .size(50.dp)
                                        .border(
                                            if (isFocused) 2.dp
                                            else 1.dp,
                                            if (isFocused) Color(0xFF474D86)
                                            else Color.LightGray, RoundedCornerShape(8.dp)
                                        )
                                        .padding(2.dp),
                                    style = androidx.compose.material.MaterialTheme.typography.h4,
                                    textAlign = TextAlign.Center)

                                Spacer(modifier = modifier.width(16.dp))
                            }
                        }
                    },
                )
            }

            Text(
                text = "Didn't receive email?",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = "You can resend code in 25 s",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }

        Column(modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Divider(modifier = modifier.fillMaxWidth())

            Button(
                onClick = onConfirmClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)) {

                Text(text = "Confirm")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OtpCodeLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        OtpCodeScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OtpCodeDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        OtpCodeScreen()
    }
}