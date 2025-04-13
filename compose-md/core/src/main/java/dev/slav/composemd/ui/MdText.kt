package dev.slav.composemd.ui

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import dev.slav.composemd.LocalComposeMd
import dev.slav.composemd.link.LocalMdLinkHandler
import dev.slav.composemd.ui.style.LocalMdTypography
import dev.slav.composemd.ui.text.MdAnnotatedStringVisitor
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
    var annotatedString: AnnotatedString by remember { mutableStateOf(buildAnnotatedString {}) }
    var inlineContent: Map<String, InlineTextContent> by remember { mutableStateOf(emptyMap()) }

    val composeMd = LocalComposeMd.current
    val typography = LocalMdTypography.current
    val linkHandler = LocalMdLinkHandler.current

    LaunchedEffect(node) {
        val annotatedStringBuilder = AnnotatedString.Builder()
        val visitor = MdAnnotatedStringVisitor(
            composeMd = composeMd,
            annotatedStringBuilder = annotatedStringBuilder,
            typography = typography,
            linkHandler = linkHandler
        )
        node.accept(visitor)
        annotatedString = annotatedStringBuilder.toAnnotatedString()
        inlineContent = visitor.inlineComponents.mapValues { (_, component) ->
            component.asInlineTextContent(style.lineHeight)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent,
        style = style
    )
}
