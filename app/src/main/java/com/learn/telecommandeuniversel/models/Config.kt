package com.learn.telecommandeuniversel.models

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.ConsumerIrManager

data class TeleConfig(
    val type: String,
    val brand: String,
    val function: String,
    val pattern: IntArray,
    val frequency: Int
)

@SuppressLint("StaticFieldLeak")
object TelecommandeConfigManager {
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    val teleConfigurations = listOf(
        TeleConfig("TV", "LG", "VOLUME -", pattern1, 38400),
        TeleConfig("TV", "LG", "VOLUME +", pattern2, 38400),
        TeleConfig("TV", "LG", "POWER", pattern3, 38400),
        TeleConfig("TV", "Samsung", "VOLUME -", pattern4, 38400),
        TeleConfig("TV", "Samsung", "VOLUME +", pattern5, 38400),
        TeleConfig("TV", "Samsung", "POWER", pattern6, 38400),
        // Ajoutez d'autres configurations si nécessaire
    )

    fun findFunctionByPattern(pattern: IntArray): String? {
        return teleConfigurations.find { it.pattern.contentEquals(pattern) }?.function
    }

    fun findFrequencyByFunction(function: String): Int? {
        return teleConfigurations.find { it.function == function }?.frequency
    }

    fun getTeleConfigurationsByTypeAndFunction(type: String, function: String): List<TeleConfig> {
        return teleConfigurations.filter { it.type == type && it.function == function }
    }

    fun sendPowerCommand(selectedBrand: String) {
        // Récupérer la configuration de la télécommande pour la marque sélectionnée
        val powerConfig = getTeleConfigurationsByBrand(selectedBrand)
            .find { it.function == "POWER" }

        // Vérifier si la configuration de la commande d'alimentation a été trouvée
        if (powerConfig != null) {
            // Envoyer la commande en utilisant le pattern ou d'autres moyens appropriés
            // Exemple de code pour envoyer la commande en utilisant le pattern (à adapter selon votre cas)
            val pattern = powerConfig.pattern
            val frequency = powerConfig.frequency
            sendCommand(pattern, frequency)
        } else {
            // Gérer le cas où la configuration de la commande d'alimentation n'est pas trouvée
            println("Configuration de la commande d'alimentation non trouvée pour la marque $selectedBrand")
        }
    }

    // Exemple de fonction d'envoi de commande (à adapter selon votre cas)
    fun sendCommand(pattern: IntArray, frequency: Int) {
        // 1. Vérifiez si le service ConsumerIrManager est disponible sur l'appareil
        val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager
            ?: run {
                println("ConsumerIrManager indisponible sur cet appareil.")
                return
            }

        // 2. Vérifiez si l'appareil prend en charge la transmission infrarouge
        if (!irManager.hasIrEmitter()) {
            println("L'appareil ne prend pas en charge la transmission infrarouge.")
            return
        }

        // 3. Vérifiez si la fréquence est supportée
        val carrierFreqs = irManager.carrierFrequencies
        if (carrierFreqs.none { it.minFrequency <= frequency && frequency <= it.maxFrequency }) {
            println("La fréquence $frequency n'est pas supportée sur cet appareil.")
            return
        }

        // 4. Envoyez la commande infrarouge
        try {
            irManager.transmit(frequency, pattern)
            println("Commande envoyée avec succès.")
        } catch (e: Exception) {
            println("Erreur lors de l'envoi de la commande : ${e.message}")
        }
    }

    private fun getTeleConfigurationsByBrand(brand: String): List<TeleConfig> {
        return teleConfigurations.filter { it.brand == brand }
    }
}

val pattern1 = intArrayOf(
    9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,
    564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,
    1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,
    564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,
    1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,39756
)

val pattern2 = intArrayOf(
    9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,
    564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,
    1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,
    564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,39756
)

val pattern3 = intArrayOf(
    9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,
    564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,
    564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,
    1692,564,564,564,1692,564,1692,564,1692,564,1692,564,39756
)

val pattern4 = intArrayOf(
    4512,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,
    1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,
    564,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,
    564,1692,564,1692,564,1692,564,46524
)

val pattern5 = intArrayOf(
    4512,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,
    1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,
    564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,
    564,46524
)

val pattern6 = intArrayOf(
    4512,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,
    1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,
    564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,46524
)
