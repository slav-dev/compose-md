package dev.slav.composemd.plugin

import dev.slav.composemd.ui.component.MdComponent
import org.commonmark.Extension
import org.commonmark.node.Node

/**
 * Compose.md plugin.
 *
 * @param T Type of the Markdown nodes supported by this plugin.
 */
interface MdPlugin<T : Node> {

    /**
     * Return a list of commonmark parser extensions used by this plugin.
     *
     * This list may be empty.
     *
     * @return List of commonmark parser extensions.
     */
    fun extensions(): List<Extension>

    /**
     * Check whether the given node is accepted by this plugin.
     *
     * @param node The node to be processed.
     *
     * @return `true` if the node is accepted by this plugin, `false` otherwise.
     */
    fun accepts(node: Node): Boolean

    /**
     * Return a composable component rendering given Markdown node.
     *
     * @param node The Markdown node to be rendered.
     *
     * @return Component rendering the Markdown node.
     */
    fun create(node: Node): MdComponent<T>
}
