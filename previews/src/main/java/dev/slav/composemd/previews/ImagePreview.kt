package dev.slav.composemd.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.slav.composemd.ComposeMd
import dev.slav.composemd.plugin.image.ImagePlugin
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.MdConfig

@Preview(showBackground = true)
@Composable
fun ImagePreview() {
    MaterialTheme {
        MdConfig(
            composeMd = ComposeMd.builder()
                .addPlugin(ImagePlugin())
                .build()
        ) {
            MdColumn(
                markdown = """
                    ![Migration Thumbnail](https://slav.dev/blob/images/migration-1-thumb.webp)

                    Image in text: ![Hilt Thumbnail](https://slav.dev/blob/images/hilt-1-thumb.webp)
                """.trimIndent(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
