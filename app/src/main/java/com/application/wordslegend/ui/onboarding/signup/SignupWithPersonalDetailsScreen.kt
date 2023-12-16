package com.application.wordslegend.ui.onboarding.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun SignupWithPersonalDetailsScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text(text = "Create an account",
                style = MaterialTheme.typography.displaySmall,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold)

            Text(text = "Please complete your profile.\nDon't worry, your data will remain private and only you can see it.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth())

            Column {
                Text(text = "Full Name")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Full Name") }
                )
            }

            Column {
                Text(text = "Date of Birth")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Date of Birth") }
                )
            }


            Column {
                Text(text = "Phone Number")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Phone Number") }
                )
            }


            Column {
                Text(text = "Country")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Country") }
                )
            }


            Column {
                Text(text = "Age")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Age") }
                )
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

                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignupWithPersonalDetailsLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        SignupWithPersonalDetailsScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SignupWithPersonalDetailsDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        SignupWithPersonalDetailsScreen()
    }
}