package com.application.wordslegend.ui.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.application.wordslegend.R
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
        Text(text = stringResource(id = R.string.app_name))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        OnboardingScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        OnboardingScreen()
    }
}