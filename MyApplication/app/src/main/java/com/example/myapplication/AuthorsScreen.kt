package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


val authorsList = listOf(
    Author("Терновский Данил", R.drawable.student_ternovskiy),
)

@Composable
fun AuthorsScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(authorsList) { author ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = author.photoRes), contentDescription = author.name, modifier = Modifier.size(64.dp))
                Spacer(Modifier.width(12.dp))
                Text(author.name, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            }
        }
    }
}
