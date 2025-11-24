package space.httpjames.kagiassistantmaterial.ui.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import androidx.core.net.toUri

@Composable
fun AttachmentPreview(uri: String, onRemove: (uri: String) -> Unit) {
    val parsedUri = remember(uri) { uri.toUri() }
    var showPreview by rememberSaveable { mutableStateOf(false) }
    val ctx = LocalContext.current

    val mime = ctx.contentResolver.getType(parsedUri)          // e.g. "image/webp"


    if (mime?.startsWith("image") ?: false) {
        Box(modifier = Modifier.size(84.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { showPreview = true }
            ) {
                AsyncImage(
                    model = parsedUri,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        onRemove(uri)
                    }
                    .background(Color.Gray),
            )

        }
    }

    if (showPreview) {
        Dialog(
            onDismissRequest = { showPreview = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable { showPreview = false }
            ) {
                AsyncImage(
                    model = parsedUri,
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
