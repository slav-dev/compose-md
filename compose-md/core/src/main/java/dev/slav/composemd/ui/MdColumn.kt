package dev.slav.composemd.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.slav.composemd.LocalComposeMd
import dev.slav.composemd.ui.style.LocalMdSpacing
import org.commonmark.node.Node

/**
 * A column containing rendered components for given Markdown text.
 *
 * @param markdown Markdown text to be rendered.
 * @param modifier Modifier to be applied to this column.
 */
@Composable
fun MdColumn(
    markdown: String,
    modifier: Modifier = Modifier,
) {
    MdColumn(
        node = LocalComposeMd.current.parse(markdown),
        modifier = modifier
    )
}

/**
 * A column containing rendered components for given Markdown node.
 *
 * @param node Root Markdown node to be rendered.
 * @param modifier Modifier to be applied to this column.
 */
@Composable
fun MdColumn(
    node: Node,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalMdSpacing.current)
    ) {
        LocalComposeMd.current
            .createComponents(node)
            .forEach { component -> component.Render() }
    }
}
