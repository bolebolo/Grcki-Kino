package com.app.grckikino.ui.live_draw

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.app.grckikino.R
import com.app.grckikino.utils.KeysAndConstants

@Composable
fun LiveDrawFragmentContent() {

    val context = LocalContext.current
    val isUrlLoaded = remember { mutableStateOf(false) }
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = false
                settings.allowContentAccess = false
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.javaScriptCanOpenWindowsAutomatically = false

                webViewClient = object : WebViewClient() {
                    override fun onReceivedError(
                        view: WebView,
                        request: WebResourceRequest,
                        error: WebResourceError
                    ) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_loading_page),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                // Učitavanje URL-a samo ako nije već učitan
                if (!isUrlLoaded.value) {
                    loadUrl(KeysAndConstants.LIVE_DRAW_URL)
                    isUrlLoaded.value = true
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}