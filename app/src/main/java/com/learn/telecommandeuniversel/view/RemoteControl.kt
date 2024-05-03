package com.learn.telecommandeuniversel.view

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
import com.learn.telecommandeuniversel.bouton.MyPowerButton
import com.learn.telecommandeuniversel.bouton.VolumeButton
import com.learn.telecommandeuniversel.ui.theme.background

@Composable
fun RemoteControl(id: Int) {
    Column(
        modifier = Modifier
            .background(background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ligne pour le bouton d'arrêt et de démarrage
        Row(
            modifier = Modifier
                .width(180.dp)
                .height(200.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MyPowerButton(id)
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
                        color = Color.White,
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
                    VolumeButton(id)
                }
            }
        }
    }
}
