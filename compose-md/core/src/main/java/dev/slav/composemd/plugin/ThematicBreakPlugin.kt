package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import org.commonmark.Extension
import org.commonmark.node.Node
import org.commonmark.node.ThematicBreak

internal class ThematicBreakPlugin : MdPlugin<ThematicBreak> {

    private val componentFactory: MdComponentFactory<ThematicBreak> =
        MdComponentFactory("ComposeMd.ThematicBreak") {
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is ThematicBreak

    override fun create(node: Node): MdComponent<ThematicBreak> {
        require(node is ThematicBreak) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
