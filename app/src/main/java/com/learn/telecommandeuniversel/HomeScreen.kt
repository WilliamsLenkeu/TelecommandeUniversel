package com.learn.telecommandeuniversel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learn.telecommandeuniversel.ui.theme.background

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .background(background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(180.dp)
                .height(270.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MyPowerButton()
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
            Text(text = "Zone de texte pour la fréquence")
        }
    }
}

@Composable
fun MyPowerButton() {
    var isClicked by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isClicked = !isClicked },
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(CircleShape)
            .width(300.dp)
            .height(300.dp)
    ) {
        if (isClicked) {
            Icon(
                Icons.Sharp.Close,
                contentDescription = "Stop",
                modifier = Modifier.size(48.dp),
                tint = Color.Red
            )
        } else {
            Icon(
                Icons.Sharp.Check,
                contentDescription = "Play",
                modifier = Modifier.size(48.dp),
                tint = Color.Green
            )
        }
    }
}
