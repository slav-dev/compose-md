package dev.slav.composemd.plugin.image.bitmap

import android.graphics.BitmapFactory
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

internal suspend fun getBitmapSize(url: String): Size =
    withContext(Dispatchers.IO) {
        try {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true

            URL(url).openConnection().inputStream.use { inputStream ->
                BitmapFactory.decodeStream(inputStream, null, options)
            }

            IntSize(options.outWidth, options.outHeight).toSize()
        } catch (_: IOException) {
            Size.Unspecified
        }
    }

internal suspend fun loadImageBitmap(url: String): ImageBitmap? =
    withContext(Dispatchers.IO) {
        try {
            URL(url).openConnection().inputStream.use { inputStream ->
                BitmapFactory.decodeStream(inputStream).asImageBitmap()
            }
        } catch (_: IOException) {
            null
        }
    }
