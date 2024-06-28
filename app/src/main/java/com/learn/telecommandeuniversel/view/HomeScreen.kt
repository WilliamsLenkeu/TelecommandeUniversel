package com.learn.telecommandeuniversel.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.bouton.HomeScreenList

@Composable
fun HomeScreen(navController: NavController, remotes: List<Remote>) {
    val sortedRemotes = remotes.sortedBy { it.marque }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(sortedRemotes) { remote ->
            HomeScreenList(navController = navController, remote = remote)
        }
    }
}
