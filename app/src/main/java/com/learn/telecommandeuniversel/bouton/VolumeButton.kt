package com.learn.telecommandeuniversel.bouton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VolumeButton(id: Int) {
    Column(
        modifier = Modifier
            .background(Color(0xFFBBBBBB))
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Sharp.KeyboardArrowUp,
                    contentDescription = "Up",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0x54171816)),
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(117.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ".",
                color = Color(0xFFBBBBBB),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Sharp.KeyboardArrowDown,
                    contentDescription = "Down",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0x54171816)),
                    tint = Color.White
                )
            }
        }
    }
}