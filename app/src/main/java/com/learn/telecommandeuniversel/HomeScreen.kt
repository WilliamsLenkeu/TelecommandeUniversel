package com.learn.telecommandeuniversel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.telecommandeuniversel.models.TelecommandeConfigManager
import com.learn.telecommandeuniversel.ui.theme.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    val configManager = TelecommandeConfigManager

    // Afficher les configurations de télécommande
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyPowerButton()
        }
        // Ligne pour le bouton de gestion du volume
        Row(
            modifier = Modifier
                .width(150.dp)
                .height(350.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
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
                    VolumeButton()
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        // Afficher les champs de configuration
        ConfigurationsSection(configManager)
    }
}

@Composable
fun ConfigurationsSection(configManager: TelecommandeConfigManager) {
    var selectedBrand by remember { mutableStateOf("") }
    var selectedFunction by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 40.dp, 10.dp, 10.dp)
            .background(Color.Black)
    ) {
        // Choix de la marque de télévision
        Text(
            text = "Choisir la marque de télévision :",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            BrandButton("LG", selectedBrand) { selectedBrand = "LG" }
            Spacer(modifier = Modifier.width(10.dp))
            BrandButton("Samsung", selectedBrand) { selectedBrand = "Samsung" }
        }

        // Choix de la fonction
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Choisir la fonction :",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            configManager.teleConfigurations
                .filter { it.type == selectedBrand }
                .forEach { config ->
                    FunctionButton(config.function, selectedFunction) { selectedFunction = config.function }
                }
        }

        // Afficher les configurations correspondantes
        Spacer(modifier = Modifier.height(20.dp))
        if (selectedBrand.isNotEmpty() && selectedFunction.isNotEmpty()) {
            // Vous pouvez appeler ici les fonctions pour interagir avec la télévision
            Button(onClick = {
                when (selectedFunction) {
                    "POWER" -> configManager.sendPowerCommand(selectedBrand)
                    /*"VOLUME +" -> configManager.sendVolumePlusCommand(selectedBrand)
                    "VOLUME -" -> configManager.sendVolumeMinusCommand(selectedBrand)
                    */// Ajoutez d'autres fonctions ici si nécessaire
                }
            }) {
                Text(text = selectedFunction)
            }
        }
    }
}

@Composable
fun BrandButton(text: String, selectedBrand: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(if (text == selectedBrand) Color.Gray else Color.White)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = if (text == selectedBrand) Color.White else Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FunctionButton(text: String, selectedFunction: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(if (text == selectedFunction) Color.Gray else Color.White)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = if (text == selectedFunction) Color.White else Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}


