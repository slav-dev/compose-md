package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import dev.slav.composemd.ui.MdText
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdTypography
import org.commonmark.Extension
import org.commonmark.node.Node
import org.commonmark.node.Paragraph

internal class ParagraphPlugin : MdPlugin<Paragraph> {

    private val componentFactory: MdComponentFactory<Paragraph> =
        MdComponentFactory("ComposeMd.Paragraph") { paragraph ->
            MdText(
                node = paragraph,
                modifier = Modifier.fillMaxWidth(),
                style = LocalMdTypography.current.paragraph
            )
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is Paragraph

    override fun create(node: Node): MdComponent<Paragraph> {
        require(node is Paragraph) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
