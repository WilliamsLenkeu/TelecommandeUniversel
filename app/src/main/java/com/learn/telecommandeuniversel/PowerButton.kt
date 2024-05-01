package com.learn.telecommandeuniversel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x54FA6464)),
                tint = Color.Red
            )
        } else {
            Icon(
                Icons.Sharp.Check,
                contentDescription = "Play",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x5485FA64)),
                tint = Color.Green
            )
        }

    }
}
