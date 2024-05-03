package com.learn.telecommandeuniversel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.learn.telecommandeuniversel.models.Device
import com.learn.telecommandeuniversel.ui.theme.TelecommandeUniverselTheme
import com.learn.telecommandeuniversel.ui.theme.background
import com.learn.telecommandeuniversel.view.HomeScreen
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devices = readDevicesFromJson()

        setContent {
            TelecommandeUniverselTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background
                ) {
                    HomeScreen(devices = devices)
                }
            }
        }
    }

    private fun readDevicesFromJson(): List<Device> {
        val inputStream = assets.open("devices.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonText = bufferedReader.use { it.readText() }
        val devicesList = mutableListOf<Device>()

        val jsonArray = JSONArray(jsonText)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val device = Device(
                jsonObject.getInt("id"),
                jsonObject.getString("type"),
                jsonObject.getString("brand"),
                jsonObject.getString("function"),
                getIntArrayFromJsonArray(jsonObject.getJSONArray("pattern")),
                jsonObject.getInt("frequency")
            )
            devicesList.add(device)
        }
        return devicesList
    }

    private fun getIntArrayFromJsonArray(jsonArray: JSONArray): IntArray {
        val intArray = IntArray(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            intArray[i] = jsonArray.getInt(i)
        }
        return intArray
    }
}