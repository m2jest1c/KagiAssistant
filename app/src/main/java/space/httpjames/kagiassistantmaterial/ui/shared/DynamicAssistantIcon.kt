package space.httpjames.kagiassistantmaterial.ui.shared

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Scale
import space.httpjames.kagiassistantmaterial.R
import java.io.File

@Composable
fun DynamicAssistantIcon(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val prefs = remember(context) {
        context.getSharedPreferences("assistant_prefs", Context.MODE_PRIVATE)
    }

    // if we have a companion set, show the companion icon
    if (prefs.getString("companion", null) != null) {
        // read the file from the cache dir
        val svgFile = File(context.cacheDir, "companion_${prefs.getString("companion", null)}.svg")
        if (svgFile.exists()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(svgFile)
                    .decoderFactory(SvgDecoder.Factory())
                    .scale(Scale.FIT)
                    .build(),
                contentDescription = null,
                modifier = modifier
            )
        }
    } else {
        Icon(
            painter = painterResource(R.drawable.fetch_ball_icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = modifier
        )
    }

}