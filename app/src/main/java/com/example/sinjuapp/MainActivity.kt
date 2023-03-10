package com.example.sinjuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinjuapp.ui.theme.SinjuAppTheme
import com.example.sinjuapp.utils.UrlUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SinjuAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonLinks(UrlUtils.LINKS)
                }
            }
        }
    }
}

@Composable
fun ButtonLinks(links: List<UrlModel>) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (link in links) {
            OutlinedButton(
                onClick = {
                    uriHandler.openUri(link.url)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFd3d3d3)),
                border = BorderStroke(2.dp, Color(0xFF444548)),
            ) {
                BtnText(link.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SinjuAppTheme {
        ButtonLinks(UrlUtils.LINKS)
    }
}

@Composable
fun BtnText(text: String) {
    Text(
        text = text,
        fontSize = getButtonSize(text).sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

fun getButtonSize(text: String): Int {
    return when (text.length) {
        in 0..3 -> 80
        in 4..6 -> 64
        else -> 48
    }
}