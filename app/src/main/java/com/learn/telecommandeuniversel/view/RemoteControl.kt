package com.learn.telecommandeuniversel.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.learn.telecommandeuniversel.R
import com.learn.telecommandeuniversel.bouton.MyPowerButton
import com.learn.telecommandeuniversel.bouton.VolumeButton
import com.learn.telecommandeuniversel.models.Function
import com.learn.telecommandeuniversel.models.Model
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.ui.theme.background
import com.learn.telecommandeuniversel.ui.theme.background2
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

@Composable
fun RemoteControl(id: Int, navController: NavController) {
    val context = LocalContext.current
    val Remote = getRemoteById(context, id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(200.dp)
                        .background(background2),
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .background(background)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val icon = painterResource(id = getIconResourceId(Remote.type))
                Image(
                    painter = icon,
                    contentDescription = "Device Icon",
                    modifier = Modifier
                        .size(105.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = Remote.type,
                    color = Color.White,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = Remote.marque,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            // Ligne pour le bouton d'arrêt et de démarrage
            Row(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MyPowerButton(Remote, context)
            }
            Row(
                modifier = Modifier
                    .width(100.dp)
                    .height(300.dp)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VolumeButton(id = Remote.id)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

private fun getRemoteById(context: Context, id: Int): Remote {
    // Utilisation du contexte pour accéder au fichier JSON dans le dossier assets
    val inputStream = context.assets.open("Irdb.Json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = bufferedReader.use { it.readText() }

    val jsonObject = JSONObject(jsonText)
    val remotesArray = jsonObject.getJSONArray("remotes")

    for (i in 0 until remotesArray.length()) {
        val remoteObject = remotesArray.getJSONObject(i)
        if (remoteObject.getInt("id") == id) {
            val marque = remoteObject.getString("marque")
            val type = remoteObject.getString("type")
            val modelsArray = remoteObject.getJSONArray("models")
            val models = mutableListOf<Model>()

            for (j in 0 until modelsArray.length()) {
                val modelObject = modelsArray.getJSONObject(j)
                val modelName = modelObject.getString("nom")
                val functionsArray = modelObject.getJSONArray("fonction")
                val functions = mutableListOf<Function>()

                for (k in 0 until functionsArray.length()) {
                    val functionObject = functionsArray.getJSONObject(k)
                    val functionName = functionObject.getString("nom")
                    val patternArray = functionObject.getJSONArray("pattern")
                    val pattern = IntArray(patternArray.length())

                    for (l in 0 until patternArray.length()) {
                        pattern[l] = patternArray.getInt(l)
                    }

                    val frequency = functionObject.getInt("frequence")

                    functions.add(Function(functionName, pattern, frequency))
                }

                models.add(Model(modelName, functions))
            }

            return Remote(id, marque, type, models)
        }
    }

    // Si aucune correspondance trouvée, renvoyer une Remote vide ou lancer une exception
    // selon vos besoins
    throw NoSuchElementException("Aucune télécommande trouvée pour l'ID $id")
}

private fun getIconResourceId(type: String): Int {
    return when (type) {
        "Tv" -> R.drawable.tv
        "DVD_PLAYER" -> R.drawable.cd
        "Air_conditioner" -> R.drawable.climatiseur
        "Sound_bar" -> R.drawable.stereo
        else -> R.drawable.device
    }
}