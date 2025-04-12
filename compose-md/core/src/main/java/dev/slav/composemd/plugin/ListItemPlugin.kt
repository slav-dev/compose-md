package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.slav.composemd.node.ChildrenWrapper
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdSpacing
import org.commonmark.Extension
import org.commonmark.node.ListItem
import org.commonmark.node.Node

internal class ListItemPlugin : MdPlugin<ListItem> {

    private val componentFactory: MdComponentFactory<ListItem> =
        MdComponentFactory("ComposeMd.ListItem") { listItem ->
            val childrenWrapper = ChildrenWrapper(listItem)
            CompositionLocalProvider(
                LocalMdSpacing provides 0.dp
            ) {
                MdColumn(
                    node = childrenWrapper,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is ListItem

    override fun create(node: Node): MdComponent<ListItem> {
        require(node is ListItem) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
