package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.slav.composemd.node.ChildrenWrapper
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdIndent
import dev.slav.composemd.ui.style.LocalMdSpacing
import org.commonmark.Extension
import org.commonmark.node.BlockQuote
import org.commonmark.node.Node

internal class BlockQuotePlugin : MdPlugin<BlockQuote> {

    private val componentFactory: MdComponentFactory<BlockQuote> =
        MdComponentFactory("ComposeMd.BlockQuote") { blockQuote ->
            val childrenWrapper = ChildrenWrapper(blockQuote)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = LocalMdIndent.current)
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(LocalMdSpacing.current),
                verticalAlignment = Alignment.CenterVertically
            ) {
                VerticalDivider()
                MdColumn(
                    node = childrenWrapper,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is BlockQuote

    override fun create(node: Node): MdComponent<BlockQuote> {
        require(node is BlockQuote) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
