package com.example.myapplication

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun GameRulesScreen() {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = false
            loadUrl("file:///android_res/raw/rules.html")
        }
    }, modifier = Modifier.fillMaxSize())
}
