package com.learn.telecommandeuniversel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learn.telecommandeuniversel.ui.theme.TelecommandeUniverselTheme
import com.learn.telecommandeuniversel.ui.theme.background

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
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .background(background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .width(180.dp)
                .height(270.dp)
                .padding(10.dp)
                .background(Color.Magenta)
        ) {
            Text(text = "Bouton pour allumer et eteindre")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(10.dp)

                .background(Color.Red)
        ) {
            Text(text = "Autres boutons")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(10.dp, 80.dp, 10.dp, 10.dp)
                .background(Color.Black)
        ) {
            Text(text = "Zone de texte pour la frequence")
        }
    }
}