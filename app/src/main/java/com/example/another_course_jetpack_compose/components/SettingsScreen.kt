package com.example.another_course_jetpack_compose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {

    var userName by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Tu nombre de usuario es: $userName")

        TextField(
            value = userName,
            label = {
                Text(text = "Ingresa tu nombre de usuario")
            },
            placeholder = {
                Text(text = "Nombre de usuario")
            },
            onValueChange = { userName = it },
            isError = userName.isEmpty(),
        )

        Spacer(modifier = Modifier.height(30.dp))

        var composeSelected by remember { mutableStateOf(false) }
        var xmlSelected by remember { mutableStateOf(false) }

        Row {
            FilterChip(
                selected = composeSelected,
                onClick = { composeSelected = !composeSelected },
                label = {
                    Text(text = "Compose")
                },
                leadingIcon = {
                    AnimatedVisibility(visible = composeSelected) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.width(15.dp))

            FilterChip(
                selected = xmlSelected,
                onClick = { xmlSelected = !xmlSelected },
                label = {
                    Text(text = "XML")
                },
                leadingIcon = {
                    AnimatedVisibility(visible = xmlSelected) {
                        Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        var composeSkills by remember { mutableFloatStateOf(0f) }

        Text("Compose Skills: ${composeSkills.toInt()}")

        Slider(
            value = composeSkills,
            onValueChange = { newValue ->
                composeSkills = newValue
            },
            steps = 4,
            valueRange = 0f .. 5f
        )

        Spacer(modifier = Modifier.height(30.dp))

        var darkModeEnabled by remember { mutableStateOf(true) }

        Text(text = "Dark Mode enabled: $darkModeEnabled")

        Switch(
            thumbContent = {
                AnimatedVisibility(visible = darkModeEnabled) {
                    Icon(imageVector = Icons.Filled.DarkMode, contentDescription = null)
                }
            },
            checked = darkModeEnabled,
            onCheckedChange = { newValue ->
                darkModeEnabled = newValue
            }
        )
    }
}
