package com.application.wordslegend.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.R
import com.application.wordslegend.ui.theme.WordsLegendTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            Row(modifier = modifier.background(color = Color.LightGray, shape = RoundedCornerShape(50)), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                }
            }

            Spacer(modifier = modifier.weight(1f))

            Row(modifier = modifier.background(color = Color.LightGray, shape = RoundedCornerShape(50)), verticalAlignment = Alignment.CenterVertically) {

            }
        }

        Text(text = "WORDS LEGEND",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold,
            modifier = modifier.fillMaxWidth(), 
            textAlign = TextAlign.Center)

        Column(modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth()) {
                Text(text = "LEVEL 1", style = MaterialTheme.typography.titleLarge)
            }

            Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth()) {
                Text(text = "CATEGORIES", style = MaterialTheme.typography.titleLarge)
            }

            Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth()) {
                Text(text = "INFINITE", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenLightThemePreview() {
    WordsLegendTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkThemePreview() {
    WordsLegendTheme(darkTheme = true) {
        HomeScreen()
    }
}