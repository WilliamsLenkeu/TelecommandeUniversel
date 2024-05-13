package com.learn.telecommandeuniversel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.telecommandeuniversel.models.Function
import com.learn.telecommandeuniversel.models.Model
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.ui.theme.TelecommandeUniverselTheme
import com.learn.telecommandeuniversel.ui.theme.background
import com.learn.telecommandeuniversel.view.HomeScreen
import com.learn.telecommandeuniversel.view.RemoteControl
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TelecommandeUniverselTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background
                ) {
                    Text(text = "Saloute")
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home"){
                val remotes = remember { readRemotesFromJson() }
                HomeScreen(navController = navController, remotes = remotes)
            }
            composable("remote/{remoteId}") { backStackEntry ->
                val remoteId = backStackEntry.arguments?.getString("remoteId")?.toIntOrNull()
                val remotes = remember { readRemotesFromJson() }
                val remote = remotes.find { it.id == remoteId }
                remote?.let {
                    RemoteControlScreen(it.id, navController)
                }
            }
        }
    }

    @Composable
    fun RemoteControlScreen(remoteId: Int, navController: NavHostController) {
        RemoteControl(remoteId, navController)
    }

    private fun readRemotesFromJson(): List<Remote> {
        val inputStream = applicationContext.assets.open("Irdb.Json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonText = bufferedReader.use { it.readText() }
        val remotesList = mutableListOf<Remote>()

        val jsonObject = JSONObject(jsonText)
        val jsonArray = jsonObject.getJSONArray("remotes")
        for (i in 0 until jsonArray.length()) {
            val remoteObject = jsonArray.getJSONObject(i)
            val remote = Remote(
                remoteObject.getString("id").toInt(),
                remoteObject.getString("marque"),
                remoteObject.getString("type"),
                parseModels(remoteObject.getJSONArray("models"))
            )
            remotesList.add(remote)
        }
        return remotesList
    }

    private fun parseModels(jsonArray: JSONArray): List<Model> {
        val modelsList = mutableListOf<Model>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val model = Model(
                jsonObject.getString("nom"),
                parseFunctions(jsonObject.getJSONArray("fonction"))
            )
            modelsList.add(model)
        }
        return modelsList
    }

    private fun parseFunctions(jsonArray: JSONArray): List<Function> {
        val functionsList = mutableListOf<Function>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val function = Function(
                jsonObject.getString("nom"),
                getIntArrayFromJsonArray(jsonObject.getJSONArray("pattern")),
                jsonObject.getInt("frequence")
            )
            functionsList.add(function)
        }
        return functionsList
    }

    private fun getIntArrayFromJsonArray(jsonArray: JSONArray): IntArray {
        val intArray = IntArray(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            intArray[i] = jsonArray.getInt(i)
        }
        return intArray
    }
}