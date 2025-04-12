package dev.slav.composemd.ui.lazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.slav.composemd.LocalComposeMd
import dev.slav.composemd.ui.style.LocalMdSpacing
import org.commonmark.node.Node

/**
 * A lazy column containing rendered components for given Markdown text.
 *
 * @param markdown Markdown text to be rendered.
 * @param modifier Modifier to be applied to this column.
 */
@Composable
fun MdLazyColumn(
    markdown: String,
    modifier: Modifier = Modifier,
) {
    MdLazyColumn(
        node = LocalComposeMd.current.parse(markdown),
        modifier = modifier
    )
}

/**
 * A lazy column containing rendered components for given Markdown node.
 *
 * @param node Root Markdown node to be rendered.
 * @param modifier Modifier to be applied to this column.
 */
@Composable
fun MdLazyColumn(
    node: Node,
    modifier: Modifier = Modifier
) {
    val components = LocalComposeMd.current.createComponents(node)
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalMdSpacing.current)
    ) {
        mdItems(components = components)
    }
}
