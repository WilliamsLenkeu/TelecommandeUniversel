package com.learn.telecommandeuniversel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        // Ligne pour le bouton d'arret et de demarrage
        Row(
            modifier = Modifier
                .width(180.dp)
                .height(270.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MyPowerButton()
        }
        // Ligne pour le bouton de gestion du volume
        Row(
            modifier = Modifier
                .width(150.dp)
                .height(350.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "VOLUME",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(90.dp))
                        .width(100.dp)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VolumeButton()
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(10.dp, 40.dp, 10.dp, 10.dp)
                .background(Color.Black)
        ) {
            Text(text = "Zone de texte pour la fréquence")
        }
    }
}
