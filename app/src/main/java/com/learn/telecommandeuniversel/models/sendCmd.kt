package com.learn.telecommandeuniversel.models

import android.content.Context
import android.hardware.ConsumerIrManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.getSystemService

@Composable
fun sendIrCommand(context: Context, frequency: Int, pattern: IntArray) {
    val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    if (irManager != null && irManager.hasIrEmitter()) {
        Log.w("Success", "Il y a un émetteur")
        irManager.transmit(frequency, pattern)
    } else {
        Log.e("Error", "Il n'y a pas d'émetteur ou l'accès à l'émetteur est restreint.")
    }
}