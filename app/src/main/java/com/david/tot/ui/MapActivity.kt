package com.david.tot.ui

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.david.tot.ui.theme.TotTheme
import com.david.tot.util.MAP_MARKER

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val longitude = bundle!!.getDouble("longitude")
        val latitude = bundle!!.getDouble("latitude")
        setContent {
            TotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapScreen(longitude,latitude)
                }
            }
        }
    }
}

@Composable
fun MapScreen(longitude:Double, latitude:Double){
    val mapUrl = "$MAP_MARKER=$longitude,$latitude"
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(mapUrl)
        }
    }, update = {
        it.loadUrl(mapUrl)
    })
}