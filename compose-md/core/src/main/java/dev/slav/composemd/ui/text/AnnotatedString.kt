package dev.slav.composemd.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import dev.slav.composemd.link.LocalMdLinkHandler
import dev.slav.composemd.link.MdLinkHandler
import dev.slav.composemd.ui.style.LocalMdTypography
import dev.slav.composemd.ui.style.MdTypography
import org.commonmark.node.Node

/**
 * Create [AnnotatedString] text from given Markdown node.
 *
 * @param node The Markdown node to be appended.
 * @param typography Markdown typography styles.
 * @param linkHandler Markdown link URL handler.
 *
 * @return This builder.
 */
@Composable
fun AnnotatedString.Companion.fromMarkdown(
    node: Node,
    typography: MdTypography = LocalMdTypography.current,
    linkHandler: MdLinkHandler = LocalMdLinkHandler.current
): AnnotatedString =
    buildAnnotatedString builder@{
        val visitor = MdAnnotatedStringVisitor(
            annotatedStringBuilder = this@builder,
            typography = typography,
            linkHandler = linkHandler
        )
        node.accept(visitor)
    }
