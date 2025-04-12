package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdTypography
import org.commonmark.Extension
import org.commonmark.node.HtmlBlock
import org.commonmark.node.Node

internal class HtmlBlockPlugin : MdPlugin<HtmlBlock> {

    private val componentFactory: MdComponentFactory<HtmlBlock> =
        MdComponentFactory("ComposeMd.HtmlBlock") { htmlBlock ->
            val annotatedHtml = AnnotatedString.fromHtml(htmlBlock.literal.trimEnd('\n'))
            Text(
                text = annotatedHtml,
                modifier = Modifier.fillMaxWidth(),
                style = LocalMdTypography.current.paragraph
            )
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is HtmlBlock

    override fun create(node: Node): MdComponent<HtmlBlock> {
        require(node is HtmlBlock) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
