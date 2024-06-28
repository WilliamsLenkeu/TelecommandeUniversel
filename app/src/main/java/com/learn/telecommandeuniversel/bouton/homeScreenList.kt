package com.learn.telecommandeuniversel.bouton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.telecommandeuniversel.R
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.ui.theme.background2
import com.learn.telecommandeuniversel.view.RemoteControl

@Composable
fun HomeScreenList(navController: NavController, remote: Remote) {
    val icon = getIconForDeviceType(remote.type)
    var showRemoteControl by remember { mutableStateOf(false) }

    /* if (!showRemoteControl) { */
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(background2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Device Icon",
                modifier = Modifier
                    .size(75.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Column(
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = remote.marque,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .width(230.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    showRemoteControl = true
                    navController.navigate("remote/${remote.id}")
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF020520))
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowRight,
                    contentDescription = "Continue",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun getIconForDeviceType(deviceType: String): Int {
    return when (deviceType) {
        "Tv" -> R.drawable.tv
        "DVD_PLAYER" -> R.drawable.cd
        "Audio" -> R.drawable.stereo
        "Sound_bar" -> R.drawable.stereo
        "Air_conditioner" -> R.drawable.climatiseur
        "Blu-ray" -> R.drawable.blu_ray
        else -> R.drawable.device
    }
}