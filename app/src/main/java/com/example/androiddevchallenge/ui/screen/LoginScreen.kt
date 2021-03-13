/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.util.baselineHeight

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        val centerHorizontally = Modifier.align(Alignment.CenterHorizontally)
        Text(
            modifier = Modifier
                .baselineHeight(topPadding = 184.dp)
                .then(centerHorizontally),
            color = MaterialTheme.colors.onBackground,
            text = stringResource(R.string.text_login_email),
            style = MaterialTheme.typography.h1
        )

        EmailField()
        PasswordField()
        TermsConditions()
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            ),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            )
        ) {
            Text(text = stringResource(R.string.button_login))
        }
    }
}

@Composable
fun EmailField(toValidate: Boolean = false) {
    var text by rememberSaveable { mutableStateOf("") }
    val invalidInput = (text.count() < 5 || '@' !in text) && toValidate
    OutlinedTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(64.dp),
        value = text,
        onValueChange = { text = it },
        isError = invalidInput,
        label = { Text(text = stringResource(R.string.label_email_address)) },
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground)
    )
}

@Composable
fun PasswordField(toValidate: Boolean = false) {
    var text by rememberSaveable { mutableStateOf("") }
    val invalidInput = text.count() < 8 && toValidate
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        value = text,
        onValueChange = { text = it },
        isError = invalidInput,
        placeholder = { Text(text = stringResource(R.string.placeholder_password)) },
        label = { Text(text = stringResource(R.string.label_password)) },
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors
                .onBackground
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun TermsConditions() {
    val noticePart1 = stringResource(R.string.log_in_notice_1)
    val noticePart2 = stringResource(R.string.log_in_notice_2)
    val noticePart3 = stringResource(R.string.log_in_notice_3)
    val terms = stringResource(R.string.terms_of_use)
    val policy = stringResource(R.string.privacy_policy)
    val notice = buildAnnotatedString {
        val underline = SpanStyle(textDecoration = TextDecoration.Underline)
        append(noticePart1)
        pushStyle(underline)
        append(terms)
        pop()
        append(noticePart2)
        pushStyle(underline)
        append(policy)
        pop()
        append(noticePart3)
    }
    Text(
        text = notice,
        modifier = Modifier
            .baselineHeight(topPadding = 24.dp, bottomPadding = 16.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body2
    )
}
