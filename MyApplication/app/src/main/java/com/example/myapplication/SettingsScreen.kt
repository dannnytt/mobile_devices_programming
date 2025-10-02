package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var speed by rememberSaveable { mutableStateOf(1f) }
    var maxCockroachesText by rememberSaveable { mutableStateOf("5") }
    var bonusIntervalText by rememberSaveable { mutableStateOf("10") }
    var roundDurationText by rememberSaveable { mutableStateOf("60") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Скорость игры: ${speed.toInt()}")
        Slider(
            value = speed,
            onValueChange = { speed = it.coerceIn(1f, 10f) },
            valueRange = 1f..10f
        )

        OutlinedTextField(
            value = maxCockroachesText,
            onValueChange = { maxCockroachesText = it },
            label = { Text("Максимум тараканов") },
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = bonusIntervalText,
            onValueChange = { bonusIntervalText = it },
            label = { Text("Интервал бонусов (сек)") },
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = roundDurationText,
            onValueChange = { roundDurationText = it },
            label = { Text("Длительность раунда (сек)") },
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Button(onClick = {
            val maxCockroaches = maxCockroachesText.toIntOrNull()?.coerceIn(1, 50) ?: 5
            val bonusInterval = bonusIntervalText.toIntOrNull()?.coerceIn(1, 60) ?: 10
            val roundDuration = roundDurationText.toIntOrNull()?.coerceIn(10, 600) ?: 60

            maxCockroachesText = maxCockroaches.toString()
            bonusIntervalText = bonusInterval.toString()
            roundDurationText = roundDuration.toString()
        }) {
            Text("Сохранить настройки")
        }
    }
}
