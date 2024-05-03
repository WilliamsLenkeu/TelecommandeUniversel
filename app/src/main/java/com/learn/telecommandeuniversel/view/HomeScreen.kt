package com.learn.telecommandeuniversel.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.learn.telecommandeuniversel.models.Device
import com.learn.telecommandeuniversel.models.HomeScreenList

@Composable
fun HomeScreen(devices: List<Device>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(devices) { device ->
            HomeScreenList(device = device)
        }
    }
}