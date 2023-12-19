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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

@Composable
fun EmailComponent(modifier: Modifier = Modifier, email: String = "", onEmailUpdate: (String) -> Unit = {}, focusManager: FocusManager) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailUpdate,
        label = { Text(text = "Email") },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = Color.LightGray)
    )
}

@Composable
fun PasswordComponent(modifier: Modifier = Modifier, password: String = "", onPasswordUpdate: (String) -> Unit = {}, focusManager: FocusManager) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordUpdate,
        label = { Text(text = "Password") },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
        },
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                modifier = modifier.size(24.dp),
                imageVector = Icons.Rounded.VisibilityOff,
                contentDescription = null)
        },
        shape = RoundedCornerShape(30),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = Color.LightGray)
    )
}