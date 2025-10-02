package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainTabs() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Регистрация", "Правила", "Авторы", "Настройки")

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.statusBarsPadding()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTab) {
            0 -> PlayerRegistrationForm()
            1 -> GameRulesScreen()
            2 -> AuthorsScreen()
            3 -> SettingsScreen()
        }
    }
}
