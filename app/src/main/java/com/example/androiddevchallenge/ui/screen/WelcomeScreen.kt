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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.util.baselineHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme().not()) R.drawable.ic_light_welcome_bg
                else R.drawable.ic_dark_welcome_bg
            ),
            contentDescription = "Welcome background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 72.dp)
        ) {
            Image(
                painter = painterResource(
                    if (isSystemInDarkTheme().not()) R.drawable.ic_light_welcome_illos
                    else R.drawable.ic_dark_welcome_illos
                ),
                contentDescription = "Logo Illos",
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.CenterEnd
            )

            val centerHorizontally = Modifier.align(Alignment.CenterHorizontally)

            Image(
                painter = painterResource(
                    id = if (isSystemInDarkTheme().not()) R.drawable.ic_light_logo
                    else R.drawable.ic_dark_logo
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(top = 48.dp)
                    .wrapContentSize()
                    .then(centerHorizontally)
            )

            Text(
                text = stringResource(R.string.text_sub_logo),
                modifier = Modifier
                    .baselineHeight(topPadding = 24.dp, bottomPadding = 40.dp)
                    .then(centerHorizontally),
                style = MaterialTheme.typography.subtitle1
            )

            val buttonModifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp)

            Button(
                onClick = { onRegisterClick() },
                modifier = buttonModifier,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(text = stringResource(R.string.button_create_account))
            }

            TextButton(
                onClick = { onLoginClick() },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .then(buttonModifier),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(text = stringResource(R.string.button_login))
            }
        }
    }
}
