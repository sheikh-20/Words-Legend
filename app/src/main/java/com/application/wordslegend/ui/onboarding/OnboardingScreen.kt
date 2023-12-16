package com.application.wordslegend.ui.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.wordslegend.R
import com.application.wordslegend.ui.theme.WordsLegendTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onClick: () -> Unit = { }) {
    val coroutineScope = rememberCoroutineScope()
    val pager = rememberPagerState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Spacer(modifier = modifier.weight(1f))

        Column(
            modifier = modifier.wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(32.dp)) {

            OnboardingContent(state = pager)

            HorizontalPagerIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                pagerState = pager,
                activeColor = Color.Red)
        }

        Column(modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Divider(modifier = modifier.fillMaxWidth())

            OutlinedButton(onClick = onClick, modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .requiredHeight(50.dp)) {
                Text(text = "GET STARTED")
            }

            Button(
                onClick = {   },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .requiredHeight(50.dp)) {

                Text(text = "I HAVE ALREADY HAVE AN ACCOUNT")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnboardingContent(modifier: Modifier = Modifier, state: PagerState = rememberPagerState()) {

    val datasource = listOf<HorizontalData>(
        HorizontalData(
            image = R.drawable.ic_onboard_one,
            title = "Play words legend whenever and wherever you want"
        ),
        HorizontalData(
            image = R.drawable.ic_onboard_two,
            title = "Find fun and interesting words to boost up your knowledge"
        ),
        HorizontalData(
            image = R.drawable.ic_onboard_three,
            title = "Play and take words challenges together with your friends"
        ),
    )

    HorizontalPager(modifier = modifier, count = datasource.size, state = state) { currentPage ->
        when (currentPage) {
            0 -> HorizontalContent(modifier = modifier, horizontalData = datasource[currentPage])
            1 -> HorizontalContent(modifier = modifier, horizontalData = datasource[currentPage])
            2 -> HorizontalContent(modifier = modifier, horizontalData = datasource[currentPage])
        }
    }
}

data class HorizontalData(val image: Int, val title: String)

@Composable
private fun HorizontalContent(modifier: Modifier = Modifier, horizontalData: HorizontalData) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {

        Image(painter = painterResource(id = horizontalData.image), contentDescription = null,
            modifier = modifier.fillMaxWidth().size(350.dp),
            contentScale = ContentScale.FillBounds)

        Text(text = horizontalData.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 16.dp))
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