package dev.slav.composemd.ui.text

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import dev.slav.composemd.ComposeMd
import dev.slav.composemd.link.MdLinkHandler
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.style.MdTypography
import org.commonmark.node.AbstractVisitor
import org.commonmark.node.Code
import org.commonmark.node.CustomNode
import org.commonmark.node.Emphasis
import org.commonmark.node.HardLineBreak
import org.commonmark.node.HtmlInline
import org.commonmark.node.Image
import org.commonmark.node.Link
import org.commonmark.node.Node
import org.commonmark.node.SoftLineBreak
import org.commonmark.node.StrongEmphasis
import org.commonmark.node.Text

/**
 * Visitor responsible for appending stylised spans
 * from Markdown nodes to [AnnotatedString].
 *
 * @param composeMd Compose.md engine used to create inline components.
 * @param annotatedStringBuilder AnnotatedString builder.
 * @param typography Markdown typography styles.
 * @param linkHandler Markdown link URL handler.
 */
class MdAnnotatedStringVisitor(
    private val composeMd: ComposeMd,
    private val annotatedStringBuilder: AnnotatedString.Builder,
    private val typography: MdTypography,
    linkHandler: MdLinkHandler
) : AbstractVisitor() {

    private val _inlineComponents = mutableMapOf<String, MdComponent<*>>()

    /**
     * Inline components built by this visitor.
     */
    val inlineComponents: Map<String, MdComponent<*>>
        get() = _inlineComponents.toMap()

    private val linkInteractionListener = LinkInteractionListener { annotation ->
        val link = when (annotation) {
            is LinkAnnotation.Url -> annotation.url
            is LinkAnnotation.Clickable -> annotation.tag
            else -> error("Unsupported link annotation type: $annotation")
        }
        linkHandler.handle(link)
    }

    private val htmlBuilder = HtmlBuilder()

    private fun <T : Node> createInlineComponent(node: T?): String? =
        node?.let(composeMd::createComponent)
            ?.let { component ->
                val componentId = java.util.UUID.randomUUID().toString()
                _inlineComponents[componentId] = component
                return@let componentId
            }

    override fun visit(text: Text?) {
        val literal = text?.literal.orEmpty()
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendText(literal)
        } else {
            annotatedStringBuilder.append(literal)
        }
    }

    override fun visit(emphasis: Emphasis?) {
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendTag(EMPHASIS_OPENING_HTML)
            super.visit(emphasis)
            htmlBuilder.appendTag(EMPHASIS_CLOSING_HTML)
        } else {
            with(annotatedStringBuilder) {
                withStyle(typography.emphasis) {
                    super.visit(emphasis)
                }
            }
        }
    }

    override fun visit(strongEmphasis: StrongEmphasis?) {
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendTag(STRONG_EMPHASIS_OPENING_HTML)
            super.visit(strongEmphasis)
            htmlBuilder.appendTag(STRONG_EMPHASIS_CLOSING_HTML)
        } else {
            with(annotatedStringBuilder) {
                withStyle(typography.strongEmphasis) {
                    super.visit(strongEmphasis)
                }
            }
        }
    }

    override fun visit(code: Code?) {
        val literal = code?.literal.orEmpty()
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendTag(CODE_OPENING_HTML)
            htmlBuilder.appendText(literal)
            htmlBuilder.appendTag(CODE_CLOSING_HTML)
        } else {
            with(annotatedStringBuilder) {
                withStyle(typography.code.toSpanStyle()) {
                    append(literal)
                }
            }
        }
    }

    override fun visit(link: Link?) {
        val url = link?.destination.orEmpty()
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendTag(LINK_OPENING_HTML.format(url))
            super.visit(link)
            htmlBuilder.appendTag(LINK_CLOSING_HTML)
        } else {
            with(annotatedStringBuilder) {
                withLink(
                    LinkAnnotation.Url(
                        url = url,
                        styles = typography.link,
                        linkInteractionListener = linkInteractionListener
                    )
                ) {
                    super.visit(link)
                }
            }
        }
    }

    override fun visit(softLineBreak: SoftLineBreak?) {
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendText(SOFT_LINE_BREAK_TEXT)
        } else {
            annotatedStringBuilder.append(SOFT_LINE_BREAK_TEXT)
        }
    }

    override fun visit(hardLineBreak: HardLineBreak?) {
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendTag(HARD_LINE_BREAK_TEXT_HTML)
        } else {
            annotatedStringBuilder.append(HARD_LINE_BREAK_TEXT_MD)
        }
    }

    override fun visit(image: Image?) {
        val contentDescription = image?.title ?: image?.destination ?: "[Image]"
        if (htmlBuilder.isBuilding) {
            htmlBuilder.appendText(contentDescription)
        } else {
            val imageId = createInlineComponent(image)
            if (imageId != null) {
                annotatedStringBuilder.appendInlineContent(
                    id = imageId,
                    alternateText = contentDescription
                )
            } else {
                annotatedStringBuilder.append(contentDescription)
            }
        }
    }

    override fun visit(customNode: CustomNode?) {
        if (htmlBuilder.isCompleted) {
            val contentId = createInlineComponent(customNode)
            if (contentId != null) {
                annotatedStringBuilder.appendInlineContent(
                    id = contentId,
                    alternateText = ""
                )
            }
        }
    }

    override fun visit(htmlInline: HtmlInline?) {
        val htmlTag = htmlInline?.literal
        if (!htmlTag.isNullOrBlank()) {
            htmlBuilder.appendTag(htmlTag)
            if (htmlBuilder.isCompleted) {
                val annotatedHtml = AnnotatedString.fromHtml(
                    htmlString = htmlBuilder.build(),
                    linkStyles = typography.link,
                    linkInteractionListener = linkInteractionListener
                )
                annotatedStringBuilder.append(annotatedHtml)
            }
        }
    }

    companion object {
        private const val EMPHASIS_OPENING_HTML = "<em>"
        private const val EMPHASIS_CLOSING_HTML = "</em>"
        private const val STRONG_EMPHASIS_OPENING_HTML = "<b>"
        private const val STRONG_EMPHASIS_CLOSING_HTML = "</b>"
        private const val CODE_OPENING_HTML = "<tt>"
        private const val CODE_CLOSING_HTML = "</tt>"
        private const val LINK_OPENING_HTML = "<a href=\"%s\">"
        private const val LINK_CLOSING_HTML = "</a>"
        private const val SOFT_LINE_BREAK_TEXT = " "
        private const val HARD_LINE_BREAK_TEXT_HTML = "<br />"
        private const val HARD_LINE_BREAK_TEXT_MD = "\n"
    }
}
