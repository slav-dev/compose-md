package dev.slav.composemd.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.TextUnit
import org.commonmark.node.Node

/**
 * Composable component rendering a Markdown node.
 *
 * @property type Type of the node rendered by this component.
 *
 * @param node The Markdown node to be rendered.
 * @param intrinsicSize Producer of intrinsic size of the component.
 * @param content Composable content of the component.
 *
 * @param T Type of the Markdown node.
 */
class MdComponent<T : Node>(
    val type: String,
    val intrinsicSize: suspend (T) -> Size,
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

    /**
     * Return this component as inline text content.
     *
     * @param lineHeight Text line height.
     *
     * @return Inline text content with this component.
     */
    suspend fun asInlineTextContent(lineHeight: TextUnit): InlineTextContent =
        InlineTextContent(
            Placeholder(
                width = lineHeight * intrinsicSize(node).aspectRatio(),
                height = lineHeight,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Render()
            }
        }

    private fun Size.aspectRatio(): Float =
        if (isSpecified) width / height else 1f
}
