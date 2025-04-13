package dev.slav.composemd.plugin.image

import dev.slav.composemd.node.childrenSequence
import dev.slav.composemd.plugin.MdPlugin
import dev.slav.composemd.ui.component.MdComponent
import org.commonmark.Extension
import org.commonmark.node.Image
import org.commonmark.node.Node
import org.commonmark.node.Paragraph

/**
 * Compose.md plugin rendering images.
 */
class ImagePlugin : MdPlugin<Image> {

    override fun extensions(): List<Extension> = emptyList()

    override fun accepts(node: Node): Boolean =
        node is Image || (node is Paragraph && node.childrenSequence().singleOrNull() is Image)

    override fun create(node: Node): MdComponent<Image> {
        val imageNode = if (node is Paragraph) node.childrenSequence().singleOrNull() else node
        require(imageNode is Image) { "Node type not accepted: $imageNode" }
        return imageComponentFactory.create(imageNode)
    }
}
