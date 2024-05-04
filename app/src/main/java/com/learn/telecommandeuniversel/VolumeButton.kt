import android.content.Context
import android.hardware.ConsumerIrManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VolumeButton(context: Context) {
    var countValue by remember { mutableStateOf(50) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    countValue++
                    // Appeler la fonction associée à l'augmentation du volume
                    emitVolumeUpSignal(context)
                }
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowUp,
                    contentDescription = "Up",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0x54171816)),
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(117.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$countValue",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    countValue--
                    // Appeler la fonction associée à la diminution du volume
                    emitVolumeDownSignal(context)
                }
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowDown,
                    contentDescription = "Down",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0x54171816)),
                    tint = Color.White
                )
            }
        }
    }
}

fun emitVolumeUpSignal(context: Context) {
    val pattern = intArrayOf(9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,
        564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,
        564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,
        564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,
        1692,564,1692,564,39756)
    val frequency = 38400

    // Émettre le signal infrarouge pour augmenter le volume
    emitInfraredSignal(context, frequency, pattern)
}

fun emitVolumeDownSignal(context: Context) {
    val pattern = intArrayOf(9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,
        564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,
        564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,
        564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,
        564,1692,564,1692,564,39756)
    val frequency = 38400

    // Émettre le signal infrarouge pour diminuer le volume
    emitInfraredSignal(context, frequency, pattern)
}

fun emitInfraredSignal(context: Context, frequency: Int, pattern: IntArray) {
    // Obtenez l'instance de ConsumerIrManager
    val irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    // Vérifiez si l'émetteur infrarouge est disponible sur ce dispositif
    if (irManager?.hasIrEmitter() == true) {
        // Émettez le signal infrarouge en utilisant la méthode transmit
        irManager.transmit(frequency, pattern)
    } else {
        // Le dispositif ne prend pas en charge l'émission de signaux infrarouges
        // Gérer cette situation en conséquence
        println("Ce dispositif ne prend pas en charge l'émission de signaux infrarouges.")
    }
}
