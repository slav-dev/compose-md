package dev.slav.composemd.plugin.image

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import dev.slav.composemd.plugin.image.bitmap.getBitmapSize
import dev.slav.composemd.plugin.image.bitmap.loadImageBitmap
import dev.slav.composemd.plugin.image.ui.MaybeImage
import dev.slav.composemd.ui.component.MdComponentFactory
import kotlinx.coroutines.launch

internal val imageComponentFactory: MdComponentFactory<org.commonmark.node.Image> =
    MdComponentFactory(
        type = "ComposeMd.Image",
        intrinsicSize = { image -> getBitmapSize(url = image.destination) }
    ) { image ->
        val coroutineScope = rememberCoroutineScope()
        val imageUrl = image.destination
        var imageBitmap: ImageBitmap? by remember(imageUrl) { mutableStateOf(null) }

        LaunchedEffect(imageUrl) {
            coroutineScope.launch {
                imageBitmap = loadImageBitmap(imageUrl)
            }
        }

        MaybeImage(
            bitmap = imageBitmap,
            contentDescription = image.title
        )
    }
