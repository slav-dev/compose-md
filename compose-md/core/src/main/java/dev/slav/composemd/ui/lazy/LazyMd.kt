package dev.slav.composemd.ui.lazy

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import dev.slav.composemd.ui.component.MdComponent
import org.commonmark.node.Node

/**
 * Add a list of Markdown items to the lazy list.
 *
 * @param components Components rendering Markdown nodes.
 */
fun LazyListScope.mdItems(
    components: List<MdComponent<out Node>>
) {
    items(
        items = components,
        contentType = { component -> component.type }
    ) { component ->
        component.Render()
    }
}
