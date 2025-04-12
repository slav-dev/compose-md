package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import dev.slav.composemd.ui.MdText
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdTypography
import org.commonmark.Extension
import org.commonmark.node.Heading
import org.commonmark.node.Node

internal class HeadingPlugin : MdPlugin<Heading> {

    private val componentFactory: MdComponentFactory<Heading> =
        MdComponentFactory("ComposeMd.Heading") { heading ->
            val style = when (val level = heading.level) {
                LEVEL_1 -> LocalMdTypography.current.heading1
                LEVEL_2 -> LocalMdTypography.current.heading2
                LEVEL_3 -> LocalMdTypography.current.heading3
                LEVEL_4 -> LocalMdTypography.current.heading4
                LEVEL_5 -> LocalMdTypography.current.heading5
                LEVEL_6 -> LocalMdTypography.current.heading6
                else -> error("Unsupported heading level: $level")
            }
            MdText(
                node = heading,
                modifier = Modifier.fillMaxWidth(),
                style = style
            )
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is Heading && node.level in LEVEL_MIN..LEVEL_MAX

    override fun create(node: Node): MdComponent<Heading> {
        require(node is Heading) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }

    companion object {
        private const val LEVEL_MIN = 1
        private const val LEVEL_MAX = 6
        private const val LEVEL_1 = 1
        private const val LEVEL_2 = 2
        private const val LEVEL_3 = 3
        private const val LEVEL_4 = 4
        private const val LEVEL_5 = 5
        private const val LEVEL_6 = 6
    }
}
