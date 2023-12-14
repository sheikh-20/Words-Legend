package com.application.wordslegend.ui.onboarding.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        LoginScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        LoginScreen()
    }
}