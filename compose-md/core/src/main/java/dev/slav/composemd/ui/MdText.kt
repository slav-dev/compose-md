package dev.slav.composemd.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import dev.slav.composemd.LocalComposeMd
import dev.slav.composemd.ui.style.LocalMdTypography
import dev.slav.composemd.ui.text.fromMarkdown
import org.commonmark.node.Node

/**
 * Markdown text containing a single styled paragraph.
 *
 * @param markdown Markdown text to be rendered.
 * @param modifier Modifier to be applied to this text.
 * @param overflow Text overflow handling strategy.
 * @param maxLines Maximum number of lines to be displayed.
 * @param minLines Minimum number of lines to be displayed.
 * @param style Base text style of the paragraph.
 */
@Composable
fun MdText(
    markdown: String,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = LocalMdTypography.current.paragraph
) {
    MdText(
        node = LocalComposeMd.current.parse(markdown),
        modifier = modifier,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        style = style
    )
}

/**
 * Markdown text containing a single styled paragraph.
 *
 * @param node Root Markdown node to be rendered.
 * @param modifier Modifier to be applied to this text.
 * @param overflow Text overflow handling strategy.
 * @param maxLines Maximum number of lines to be displayed.
 * @param minLines Minimum number of lines to be displayed.
 * @param style Base text style of the paragraph.
 */
@Composable
fun MdText(
    node: Node,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = LocalMdTypography.current.paragraph
) {
    Text(
        text = AnnotatedString.fromMarkdown(node),
        modifier = modifier,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        style = style
    )
}
