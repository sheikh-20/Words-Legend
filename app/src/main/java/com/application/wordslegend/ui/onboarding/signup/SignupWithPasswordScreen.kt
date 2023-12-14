package com.application.wordslegend.ui.onboarding.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun SignupWithPasswordScreen(modifier: Modifier = Modifier) {

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