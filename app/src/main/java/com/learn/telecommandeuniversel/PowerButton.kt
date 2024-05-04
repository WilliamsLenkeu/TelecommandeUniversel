import android.content.Context
import android.hardware.ConsumerIrManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyPowerButton(context: Context) {
    var isClicked by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            // Inverser l'état de clic
            isClicked = !isClicked
            // Appeler la fonction associée à l'alimentation
            if (isClicked) {
                emitPowerSignal(context)
            }
        },
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(CircleShape)
            .width(300.dp)
            .height(300.dp)
    ) {
        if (isClicked) {
            Icon(
                Icons.Sharp.Close,
                contentDescription = "Stop",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x54FA6464)),
                tint = Color.Red
            )
        } else {
            Icon(
                Icons.Sharp.Check,
                contentDescription = "Play",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x5485FA64)),
                tint = Color.Green
            )
        }

    }
}

fun emitPowerSignal(context: Context) {
    // Récupérer l'instance de ConsumerIrManager
    val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    // Vérifier si l'émetteur infrarouge est disponible
    if (irManager?.hasIrEmitter() == true) {
        // Définir la fréquence et le motif du signal infrarouge pour allumer/éteindre
        val frequency = 38400 // Fréquence en Hz
        val pattern = intArrayOf(9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,
            564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,
            1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,
            1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,39756) // Modèle de signal

        // Émettre le signal infrarouge
        irManager.transmit(frequency, pattern)
    } else {
        // Gérer si l'émetteur infrarouge n'est pas disponible sur ce dispositif
        println("L'émetteur infrarouge n'est pas disponible sur ce dispositif.")
    }
}
