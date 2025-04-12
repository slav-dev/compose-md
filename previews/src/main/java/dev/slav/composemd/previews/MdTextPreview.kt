package dev.slav.composemd.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.slav.composemd.ui.MdText

@Preview(showBackground = true)
@Composable
fun MdTextPreview() {
    val markdown = """
        This paragraph contains **bold**, _italic_ and **_bold italic_** text.
        There&rsquo;s a [link](https://slav.dev/).
        It also contains some `code` and <big>some</big> <sup>HTML</sup> <sub>elements</sub>.\
        This sentence is after a hard line break.
    """.trimIndent()
    MdText(
        markdown = markdown,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}
