package com.example.memoryleakapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.memoryleakapplication.ui.theme.MemoryLeakApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryLeakApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class Screen {
    HOME, LEAKY,SAFE
}

@Composable
fun App(modifier: Modifier) {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    when (currentScreen) {
        Screen.HOME -> HomeScreen(
            onOpenLeaky = { currentScreen = Screen.LEAKY },
            onOpenSafe = { currentScreen = Screen.SAFE }
        )
        Screen.LEAKY -> LeakyScreen(
            onBack = { currentScreen = Screen.HOME }
        )
        Screen.SAFE -> SafeScreen(
            onBack = { currentScreen = Screen.HOME }
        )
    }
}