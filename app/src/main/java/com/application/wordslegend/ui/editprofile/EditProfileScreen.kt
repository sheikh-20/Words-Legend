package com.application.wordslegend.ui.editprofile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditProfileLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        EditProfileScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EditProfileDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        EditProfileScreen()
    }
}