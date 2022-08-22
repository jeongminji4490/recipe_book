package com.example.recipebook.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Man
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.recipebook.R.drawable as AppIcon
import com.example.recipebook.common.theme.*


@Composable
fun EmailField(
    @StringRes text: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(text),
                fontFamily = LightFont,
                color = Navy
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Navy,
            focusedBorderColor = Navy,
            unfocusedBorderColor = Navy
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "email",
                tint = Navy
            )
        }
    )
}

@Composable
fun PasswordField(
    @StringRes pw: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon = if (isVisible) painterResource(AppIcon.ic_visibility_on)
    else painterResource(AppIcon.ic_visibility_off)

    val visualTransformation = if (isVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(pw),
                fontFamily = LightFont,
                color = Navy
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Navy,
            focusedBorderColor = Navy,
            unfocusedBorderColor = Navy
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock",
                tint = Navy) },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@Composable
fun NameField(
    @StringRes text: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(text),
                fontFamily = LightFont,
                color = Navy
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Navy,
            focusedBorderColor = Navy,
            unfocusedBorderColor = Navy
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Man,
                contentDescription = "name",
                tint = Navy
            )
        }
    )
}