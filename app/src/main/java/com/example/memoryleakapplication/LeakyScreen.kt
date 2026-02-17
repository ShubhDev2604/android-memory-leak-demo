package com.example.memoryleakapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeakyScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    // ❌ INTENTIONAL MEMORY LEAK:
    // Adding the Activity context to a static list every time this screen is composed.
    // If you rotate the screen, the old Activity is leaked because it's still in this list.
    LaunchedEffect(Unit) {
        LeakyHolder.leakedObjects.add(context)
        // Add a large 10MB array to make the leak visible in the Profiler
        LeakyHolder.leakedObjects.add(ByteArray(10 * 1024 * 1024))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is the LEAKY screen.",
            fontSize = 20.sp
        )
        Text(
            text = "Every time you enter this screen or ROTATE the phone, 10MB + the Activity context is leaked.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Currently leaked items: ${LeakyHolder.leakedObjects.size}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
