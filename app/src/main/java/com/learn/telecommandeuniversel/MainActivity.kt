package com.learn.telecommandeuniversel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.learn.telecommandeuniversel.models.Function
import com.learn.telecommandeuniversel.models.Model
import com.learn.telecommandeuniversel.models.Remote
import com.learn.telecommandeuniversel.ui.theme.TelecommandeUniverselTheme
import com.learn.telecommandeuniversel.ui.theme.background
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import com.learn.telecommandeuniversel.view.HomeScreen
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val remotes = readRemotesFromJson()

        setContent {
            TelecommandeUniverselTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background
                ) {
                    HomeScreen(remotes = remotes)
                }
            }
        }
    }

    private fun readRemotesFromJson(): List<Remote> {
        val inputStream = assets.open("Irdb.Json")
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