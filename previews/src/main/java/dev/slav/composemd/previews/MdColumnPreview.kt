package dev.slav.composemd.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.MdConfig

/**
 * This preview renders Markdown using default styles.
 */
@Preview(showBackground = true)
@Composable
fun MdColumnPreviewDefault() {
    MdColumn(
        markdown = previewMarkdown,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/**
 * This preview renders Markdown using default Material Design 3 styles.
 */
@Preview(showBackground = true)
@Composable
fun MdColumnPreviewMaterial() {
    MaterialTheme {
        MdConfig {
            MdColumn(
                markdown = previewMarkdown,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

private val previewMarkdown = """
    # Header 1

    ## Header 2

    ### Header 3

    #### Header 4

    ##### Header 5

    ###### Header 6

    Lorem ipsum dolor sit amet, consectetur adipiscing elit,
    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
    Ut enim ad minim veniam, quis nostrud exercitation
    ullamco laboris nisi ut aliquip ex ea commodo consequat.

    Duis aute irure dolor in reprehenderit in voluptate
    velit esse cillum dolore eu fugiat nulla pariatur.
    Excepteur sint occaecat cupidatat non proident,
    sunt in culpa qui officia deserunt mollit anim id est laborum.

    ---

    Span styles: **bold**, _italic_, **_bold italic_**, `code`,
    [link][slav-dev].

    > Block quote
    >
    > > Nested block quote

    ---

    Fenced code block:

    ```
    data class User(
        val username: String,
        val password: String
    )
    ```

    Indented code block:

        data class User(
            val username: String,
            val password: String
        )


    [slav-dev]: https://slav.dev/
""".trimIndent()
