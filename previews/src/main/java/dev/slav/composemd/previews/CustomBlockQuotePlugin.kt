package dev.slav.composemd.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.slav.composemd.ComposeMd
import dev.slav.composemd.node.ChildrenWrapper
import dev.slav.composemd.plugin.MdPlugin
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.MdConfig
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import org.commonmark.Extension
import org.commonmark.node.BlockQuote
import org.commonmark.node.Node

/**
 * Custom block quote component, rendering a block quote inside a card.
 */
class CustomBlockQuotePlugin : MdPlugin<BlockQuote> {

    /**
     * A factory creating block quote components.
     */
    private val componentFactory: MdComponentFactory<BlockQuote> =
        MdComponentFactory("CustomBlockQuote") { blockQuote ->
            // Use ChildrenWrapper to avoid infinite recursion:
            val childrenWrapper = ChildrenWrapper(blockQuote)

            // Render children in a column, inside a card:
            Card(modifier = Modifier.fillMaxWidth()) {
                MdColumn(
                    node = childrenWrapper,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    /**
     * Return empty list.
     *
     * No additional Commonmark parser extensions are required.
     */
    override fun extensions(): List<Extension> =
        emptyList()

    /**
     * Accept only [BlockQuote] nodes.
     */
    override fun accepts(node: Node): Boolean =
        node is BlockQuote

    /**
     * Create a new component using the factory.
     */
    override fun create(node: Node): MdComponent<BlockQuote> {
        require(node is BlockQuote) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}

/**
 * This preview renders block quote using the default block quote plugin,
 * embedded in Compose.md engine.
 */
@Preview(showBackground = true)
@Composable
fun DefaultBlockQuotePreview() {
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

/**
 * This preview renders block quote using [CustomBlockQuotePlugin].
 */
@Preview(showBackground = true)
@Composable
fun CustomBlockQuotePreview() {
    MaterialTheme {
        MdConfig(
            composeMd = ComposeMd.builder()
                .addPlugin(CustomBlockQuotePlugin())
                .build()
        ) {
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
    > *The only difference between science
    > and screwing around is writing it down.*
    >
    > —Adam Savage
""".trimIndent()
