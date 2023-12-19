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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.R
import com.application.wordslegend.ui.onboarding.component.SocialLoginComponent
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun CreateNewPasswordScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Create new password",
                style = MaterialTheme.typography.displaySmall,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold)

            Text(
                text = "Save the new password in a safe place, if you forget it then you have to do forgot password again",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = modifier.fillMaxWidth()
            )

            Column {
                Text(text = "Create new password")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Create new password") }
                )
            }

            Column {
                Text(text = "Confirm new password")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Confirm new password") }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {})
                Text(text = "Remember me")
            }
        }

        Column(modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Divider(modifier = modifier.fillMaxWidth())

            Button(
                onClick = {   },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)) {

                Text(text = "Sign up")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateNewPasswordLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        CreateNewPasswordScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CreateNewPasswordDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        CreateNewPasswordScreen()
    }
}