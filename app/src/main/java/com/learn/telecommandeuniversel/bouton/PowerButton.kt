package com.learn.telecommandeuniversel.bouton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.content.Context
import android.util.Log
import androidx.compose.runtime.setValue
import com.learn.telecommandeuniversel.R
import com.learn.telecommandeuniversel.models.Function
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.models.sendIrCommand

@Composable
fun MyPowerButton(remote: Remote, context: Context) {
    var isClicked by remember { mutableStateOf(false) }
    val play = painterResource(id = R.drawable.fermer)
    val stop = painterResource(id = R.drawable.allumer)
    val backgroundColor = if (isClicked) Color(0x9AFA6464) else Color(0x9A85FA64)

    val functionToExecute: Function? = remote.models.flatMap { it.functions }
        .find { it.name == "Power" }

    IconButton(
        onClick = {
            isClicked = !isClicked
        },
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        if (isClicked) {
            Image(
                painter = stop,
                contentDescription = "Stop",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = play,
                contentDescription = "Play",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
        }
    }

    if (functionToExecute != null) {
        SendIrCommandComposable(isClicked, functionToExecute, context)
    }else{
        Log.w("Error", "function is null")
    }
}

@Composable
fun SendIrCommandComposable(isClicked: Boolean, functionToExecute: Function, context: Context) {
    if (isClicked) {
        sendIrCommand(context, functionToExecute.frequency, functionToExecute.pattern)
    }else{
        sendIrCommand(context, functionToExecute.frequency, functionToExecute.pattern)
    }
}