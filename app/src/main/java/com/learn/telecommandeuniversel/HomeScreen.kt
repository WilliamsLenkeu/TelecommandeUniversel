package com.learn.telecommandeuniversel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                .padding(10.dp)
                .background(Color.Magenta)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Icons.Rounded.Face
            }
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