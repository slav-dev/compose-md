package dev.slav.composemd.plugin.image.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale

@Composable
internal fun MaybeImage(
    bitmap: ImageBitmap?,
    contentDescription: String?
) {
    if (bitmap != null) {
        val painter = BitmapPainter(bitmap)
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height),
            contentScale = ContentScale.FillWidth
        )
    }
}
