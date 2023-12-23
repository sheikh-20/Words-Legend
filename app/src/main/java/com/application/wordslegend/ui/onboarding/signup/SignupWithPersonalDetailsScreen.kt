package com.application.wordslegend.ui.onboarding.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.application.wordslegend.R
import com.application.wordslegend.data.common.Resource
import com.application.wordslegend.ui.onboarding.component.convertMillisToDate
import com.application.wordslegend.ui.theme.WordsLegendTheme
import timber.log.Timber
import java.time.Instant

private const val TAG = "SignupWithPersonalDetailsScreen"
@Composable
fun SignupWithPersonalDetailsScreen(modifier: Modifier = Modifier,
                                    onContinueClick: () -> Unit = { },
                                    fullName: String = "",
                                    onFullNameChange: (String) -> Unit = { _ -> },
                                    dob: String = "",
                                    onDobChange: (String) -> Unit = { _ -> },
                                    phoneNumber: String = "",
                                    onPhoneNumberChange: (String) -> Unit = { _ -> },
                                    country: String = "",
                                    onCountryChange: () -> Unit = {  }) {

    val focusManager = LocalFocusManager.current

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Create an account ✏\uFE0F",
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Please complete your profile.\nDon't worry, your data will remain private and only you can see it.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )



            Column {
//                Text(text = "Full Name")

                OutlinedTextField(
                    value = fullName,
                    onValueChange = onFullNameChange,
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Full Name") },
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }
            Column {
//                Text(text = "Date of Birth")

                OutlinedTextField(
                    value = dob,
                    onValueChange = {},
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Date of Birth") },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(
                                imageVector = Icons.Rounded.CalendarMonth,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            Column {
//                Text(text = "Phone Number")

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Phone Number") },
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            Column {
//                Text(text = "Country")

                OutlinedTextField(
                    value = country,
                    onValueChange = { },
                    modifier = modifier.fillMaxWidth(),
                    label = { Text(text = "Country") },
                    trailingIcon = {
                        IconButton(onClick = { onCountryChange() }) {
                            Icon(
                                imageVector = Icons.Rounded.ExpandMore,
                                contentDescription = null
                            )
                        }
                    },
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }
        }


        Column(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Divider(modifier = modifier.fillMaxWidth())

            Button(
                onClick = onContinueClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .requiredHeight(50.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(50)
                    )
            ) {

                Text(text = "Continue")
            }
        }


        if (showDatePicker) {
            DatePickerDialog(onDateSelected = onDobChange) {
                showDatePicker = false
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (String) -> Unit = { _ -> },
    onDismiss: () -> Unit = { }
) {
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(datePickerState.selectedDateMillis?.convertMillisToDate() ?: "")
                onDismiss()
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
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