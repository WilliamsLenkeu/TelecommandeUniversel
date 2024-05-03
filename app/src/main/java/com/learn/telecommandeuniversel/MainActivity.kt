package com.learn.telecommandeuniversel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.learn.telecommandeuniversel.ui.theme.TelecommandeUniverselTheme
import com.learn.telecommandeuniversel.view.HomeScreen
import com.learn.telecommandeuniversel.view.RemoteControl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelecommandeUniverselTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    HomeScreen()
                    RemoteControl()
                }
            }
        }
    }
}
