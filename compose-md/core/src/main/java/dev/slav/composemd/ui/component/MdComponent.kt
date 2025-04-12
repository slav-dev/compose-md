package dev.slav.composemd.ui.component

import androidx.compose.runtime.Composable
import org.commonmark.node.Node

/**
 * Composable component rendering a Markdown node.
 *
 * @property type Type of the node rendered by this component.
 *
 * @param node The Markdown node to be rendered.
 * @param content Composable content of the component.
 *
 * @param T Type of the Markdown node.
 */
class MdComponent<T : Node>(
    val type: String,
    private val node: T,
    private val content: @Composable (T) -> Unit
) {

    /**
     * Render the Markdown node.
     */
    @Composable
    fun Render() {
        content(node)
    }
}
