package com.application.wordslegend.ui.onboarding.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun EnterPersonalDetailsScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EnterPersonalDetailsLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        EnterPersonalDetailsScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EnterPersonalDetailsDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        EnterPersonalDetailsScreen()
    }
}