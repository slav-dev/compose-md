package dev.slav.composemd.plugin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import dev.slav.composemd.node.childrenSequence
import dev.slav.composemd.ui.MdBulletListItemIndicator
import dev.slav.composemd.ui.MdColumn
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentFactory
import dev.slav.composemd.ui.style.LocalMdIndent
import dev.slav.composemd.ui.style.LocalMdListItemWidth
import org.commonmark.Extension
import org.commonmark.node.BulletList
import org.commonmark.node.Node

internal class BulletListPlugin : MdPlugin<BulletList> {

    private val componentFactory: MdComponentFactory<BulletList> =
        MdComponentFactory("ComposeMd.BulletList") { bulletList ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = LocalMdIndent.current)
            ) {
                for (child in bulletList.childrenSequence()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                    ) {
                        MdBulletListItemIndicator(
                            modifier = Modifier.width(LocalMdListItemWidth.current)
                        )
                        MdColumn(
                            node = child,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

    override fun extensions(): List<Extension> =
        emptyList()

    override fun accepts(node: Node): Boolean =
        node is BulletList

    override fun create(node: Node): MdComponent<BulletList> {
        require(node is BulletList) { "Node type not accepted: $node" }
        return componentFactory.create(node)
    }
}
