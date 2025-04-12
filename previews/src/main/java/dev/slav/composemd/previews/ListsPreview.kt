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

@Preview(showBackground = true)
@Composable
fun ListsPreview() {
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
    Bullet list:
    - Lorem ipsum dolor sit amet, consectetur adipiscing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
    - Ut enim ad minim veniam, quis nostrud exercitation
      ullamco laboris nisi ut aliquip ex ea commodo consequat.
    - Duis aute irure dolor in reprehenderit in voluptate
      velit esse cillum dolore eu fugiat nulla pariatur.
    - Excepteur sint occaecat cupidatat non proident,
      sunt in culpa qui officia deserunt mollit anim id est laborum.

    Ordered list:
    1. Lorem ipsum dolor sit amet, consectetur adipiscing elit,
       sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
    2. Ut enim ad minim veniam, quis nostrud exercitation
       ullamco laboris nisi ut aliquip ex ea commodo consequat.
    3. Duis aute irure dolor in reprehenderit in voluptate
       velit esse cillum dolore eu fugiat nulla pariatur.
    4. Excepteur sint occaecat cupidatat non proident,
       sunt in culpa qui officia deserunt mollit anim id est laborum.

    Mixed list:
    1. Bullet sublist:
       - First bullet
       - Second bullet
       - Third bullet
    2. Ordered sublist:
       1. First element
       2. Second element
       3. Third element
    3. Element with no sublist
""".trimIndent()
