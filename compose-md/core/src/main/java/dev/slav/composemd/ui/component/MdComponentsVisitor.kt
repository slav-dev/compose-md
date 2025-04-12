package dev.slav.composemd.ui.component

import dev.slav.composemd.plugin.MdPlugin
import org.commonmark.node.AbstractVisitor
import org.commonmark.node.BlockQuote
import org.commonmark.node.BulletList
import org.commonmark.node.CustomBlock
import org.commonmark.node.CustomNode
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.Heading
import org.commonmark.node.HtmlBlock
import org.commonmark.node.Image
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.LinkReferenceDefinition
import org.commonmark.node.ListItem
import org.commonmark.node.Node
import org.commonmark.node.OrderedList
import org.commonmark.node.Paragraph
import org.commonmark.node.ThematicBreak

/**
 * Visitor responsible for creating composable components
 * rendering Markdown nodes.
 *
 * @param plugins Compose.md plugins used to transform Markdown nodes.
 */
class MdComponentsVisitor(
    private val plugins: List<MdPlugin<*>>,
) : AbstractVisitor() {

    private val _components = mutableListOf<MdComponent<*>>()

    /**
     * Components built by this visitor.
     */
    val components: List<MdComponent<*>>
        get() = _components.toList()

    private fun <T : Node> createComponent(node: T?): Boolean {
        if (node == null) {
            return false
        }
        val component = plugins
            .firstOrNull { plugin -> plugin.accepts(node) }
            ?.create(node)
        if (component != null) {
            _components += component
        }
        return component != null
    }

    override fun visit(paragraph: Paragraph?) {
        if (!createComponent(paragraph)) {
            super.visit(paragraph)
        }
    }

    override fun visit(heading: Heading?) {
        if (!createComponent(heading)) {
            super.visit(heading)
        }
    }

    override fun visit(bulletList: BulletList?) {
        if (!createComponent(bulletList)) {
            super.visit(bulletList)
        }
    }

    override fun visit(orderedList: OrderedList?) {
        if (!createComponent(orderedList)) {
            super.visit(orderedList)
        }
    }

    override fun visit(listItem: ListItem?) {
        if (!createComponent(listItem)) {
            super.visit(listItem)
        }
    }

    override fun visit(blockQuote: BlockQuote?) {
        if (!createComponent(blockQuote)) {
            super.visit(blockQuote)
        }
    }

    override fun visit(image: Image?) {
        if (!createComponent(image)) {
            super.visit(image)
        }
    }

    override fun visit(fencedCodeBlock: FencedCodeBlock?) {
        if (!createComponent(fencedCodeBlock)) {
            super.visit(fencedCodeBlock)
        }
    }

    override fun visit(indentedCodeBlock: IndentedCodeBlock?) {
        if (!createComponent(indentedCodeBlock)) {
            super.visit(indentedCodeBlock)
        }
    }

    override fun visit(htmlBlock: HtmlBlock?) {
        if (!createComponent(htmlBlock)) {
            super.visit(htmlBlock)
        }
    }

    override fun visit(thematicBreak: ThematicBreak?) {
        if (!createComponent(thematicBreak)) {
            super.visit(thematicBreak)
        }
    }

    override fun visit(customNode: CustomNode?) {
        if (!createComponent(customNode)) {
            super.visit(customNode)
        }
    }

    override fun visit(customBlock: CustomBlock?) {
        if (!createComponent(customBlock)) {
            super.visit(customBlock)
        }
    }

    override fun visit(linkReferenceDefinition: LinkReferenceDefinition?) {
        if (!createComponent(linkReferenceDefinition)) {
            super.visit(linkReferenceDefinition)
        }
    }
}
