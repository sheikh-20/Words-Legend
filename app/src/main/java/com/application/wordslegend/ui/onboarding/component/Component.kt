package com.application.wordslegend.ui.onboarding.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LoginComponent(modifier: Modifier = Modifier, @DrawableRes icon: Int, @StringRes text: Int, onClick: () -> Unit) {

    OutlinedButton(onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(width = .5.dp, color =  Color.LightGray),
    ) {

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Icon(painter = painterResource(id = icon),
                contentDescription = null,
                modifier = modifier.size(30.dp),
                tint = Color.Unspecified)

            Spacer(modifier = modifier.padding(horizontal = 8.dp))

            Text(text = stringResource(id = text),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun SocialLoginComponent(modifier: Modifier = Modifier, @DrawableRes icon: Int, onClick: () -> Unit) {
    OutlinedIconButton(
        modifier = modifier.size(height = 50.dp, width = 70.dp).padding(4.dp),
        onClick = onClick,
        shape = RoundedCornerShape(30),
        border = BorderStroke(width = .5.dp, color =  Color.LightGray)) {

        Icon(
            modifier = modifier.padding(4.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}