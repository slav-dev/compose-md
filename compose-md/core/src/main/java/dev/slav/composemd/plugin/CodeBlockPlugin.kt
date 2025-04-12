package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdTypography
import org.commonmark.Extension
import org.commonmark.node.Block
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.Node

internal class CodeBlockPlugin : MdPlugin<Block> {

    private val componentFactory: MdComponentFactory<Block> =
        MdComponentFactory("ComposeMd.CodeBlock") { codeBlock ->
            val text = when (codeBlock) {
                is FencedCodeBlock -> codeBlock.literal
                is IndentedCodeBlock -> codeBlock.literal
                else -> error("Unsupported code block type: $codeBlock")
            }
            Text(
                text = text.trimEnd('\n'),
                modifier = Modifier.fillMaxWidth(),
                style = LocalMdTypography.current.code
            )
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is FencedCodeBlock || node is IndentedCodeBlock

    override fun create(node: Node): MdComponent<Block> {
        require(node is FencedCodeBlock || node is IndentedCodeBlock) {
            "Node type not accepted: $node"
        }
        return componentFactory.create(node)
    }
}
