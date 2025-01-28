package com.example.javet_cdt_integration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.caoccao.javet.interop.V8Host
import com.caoccao.javet.interop.V8Runtime
import com.example.javet_cdt_integration.cdt.CDTShell
import com.example.javet_cdt_integration.ui.theme.JavetcdtintegrationTheme

class MainActivity : ComponentActivity() {
    private val v8Runtime: V8Runtime = V8Host.getV8Instance().createV8Runtime()
    private lateinit var cdtShell: CDTShell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JavetcdtintegrationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        cdtShell = CDTShell(v8Runtime)
        cdtShell.run()
    }

    override fun onDestroy() {
        super.onDestroy()
        cdtShell.stop()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JavetcdtintegrationTheme {
        Greeting("Android")
    }
}