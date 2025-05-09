package com.psydrite.vrid_blog_app.ui.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebPageViewer(url: String) {
    val context = LocalContext.current

    AndroidView(factory = {
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    })
}