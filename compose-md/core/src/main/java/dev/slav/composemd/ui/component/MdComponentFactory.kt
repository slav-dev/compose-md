package dev.slav.composemd.ui.component

import androidx.compose.runtime.Composable
import org.commonmark.node.Node

/**
 * Factory responsible for creating composable components
 * rendering Markdown nodes of given type.
 *
 * @param type Type of the nodes rendered by components created by this factory.
 * @param content Composable content of components created by this factory.
 *
 * @param T Type of the Markdown node.
 */
class MdComponentFactory<T : Node>(
    private val type: String,
    private val content: @Composable (T) -> Unit
) {

    /**
     * Create a new component rendering given Markdown node.
     *
     * @param node The Markdown node to be rendered.
     *
     * @return Component rendering the Markdown node.
     */
    fun create(node: T): MdComponent<T> =
        MdComponent(
            type = type,
            node = node,
            content = this.content
        )
}
