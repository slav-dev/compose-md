package dev.slav.composemd.ui.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import dev.slav.composemd.link.MdLinkHandler
import dev.slav.composemd.ui.style.MdTypography
import org.assertj.core.api.Assertions.assertThat
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@DisplayName("MdAnnotatedStringVisitor")
@ExtendWith(MockitoExtension::class)
class MdAnnotatedStringVisitorTest {

    @Spy
    lateinit var annotatedStringBuilder: AnnotatedString.Builder

    @Mock
    lateinit var typography: MdTypography

    @Mock
    lateinit var linkHandler: MdLinkHandler

    @InjectMocks
    lateinit var classUnderTest: MdAnnotatedStringVisitor

    @Test
    @DisplayName(
        "GIVEN text without Markdown formatting, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the same text"
    )
    fun noFormattingShouldReturnCorrectText() {
        val document = parseMarkdown("Lorem ipsum dolor sit amet")

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text).isEqualTo("Lorem ipsum dolor sit amet")
    }

    @Test
    @DisplayName(
        "GIVEN text without Markdown formatting, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun noFormattingShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown("Lorem ipsum dolor sit amet")

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text without Markdown formatting, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without styles"
    )
    fun noFormattingShouldReturnTextWithoutStyles() {
        val document = parseMarkdown("Lorem ipsum dolor sit amet")

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with an emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the same text"
    )
    fun emphasisShouldReturnCorrectText() {
        val document = parseMarkdown("Lorem ipsum *dolor* sit amet")
        val emphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.emphasis) doReturn emphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text).isEqualTo("Lorem ipsum dolor sit amet")
    }

    @Test
    @DisplayName(
        "GIVEN text with an emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun emphasisShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown("Lorem ipsum *dolor* sit amet")
        val emphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.emphasis) doReturn emphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with an emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with correct emphasis style"
    )
    fun emphasisShouldReturnTextWithEmphasisStyle() {
        val document = parseMarkdown("Lorem ipsum *dolor* sit amet")
        val emphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.emphasis) doReturn emphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles())
            .containsExactly(AnnotatedString.Range(emphasisStyle, 12, 17))
    }

    @Test
    @DisplayName(
        "GIVEN text with a strong emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the same text"
    )
    fun strongEmphasisShouldReturnCorrectText() {
        val document = parseMarkdown("Lorem ipsum **dolor** sit amet")
        val strongEmphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.strongEmphasis) doReturn strongEmphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text).isEqualTo("Lorem ipsum dolor sit amet")
    }

    @Test
    @DisplayName(
        "GIVEN text with a strong emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun strongEmphasisShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown("Lorem ipsum **dolor** sit amet")
        val strongEmphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.strongEmphasis) doReturn strongEmphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with a strong emphasis, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with correct strong emphasis style"
    )
    fun strongEmphasisShouldReturnTextWithEmphasisStyle() {
        val document = parseMarkdown("Lorem ipsum **dolor** sit amet")
        val strongEmphasisStyle = SpanStyle(fontStyle = FontStyle.Italic)
        whenever(typography.strongEmphasis) doReturn strongEmphasisStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles())
            .containsExactly(AnnotatedString.Range(strongEmphasisStyle, 12, 17))
    }

    @Test
    @DisplayName(
        "GIVEN text with code, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the same text"
    )
    fun codeShouldReturnCorrectText() {
        val document = parseMarkdown("Lorem ipsum `dolor` sit amet")
        val codeStyle = TextStyle(fontFamily = FontFamily.Monospace)
        whenever(typography.code) doReturn codeStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text).isEqualTo("Lorem ipsum dolor sit amet")
    }

    @Test
    @DisplayName(
        "GIVEN text with code, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun codeShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown("Lorem ipsum `dolor` sit amet")
        val codeStyle = TextStyle(fontFamily = FontFamily.Monospace)
        whenever(typography.code) doReturn codeStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with code, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with correct code style"
    )
    fun codeShouldReturnTextWithEmphasisStyle() {
        val document = parseMarkdown("Lorem ipsum `dolor` sit amet")
        val codeSpanStyle = SpanStyle(fontFamily = FontFamily.Monospace)
        val codeStyle = TextStyle(fontFamily = FontFamily.Monospace)
        whenever(typography.code) doReturn codeStyle

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles())
            .containsExactly(AnnotatedString.Range(codeSpanStyle, 12, 17))
    }

    @Test
    @DisplayName(
        "GIVEN text with a link, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the same text"
    )
    fun linkShouldReturnCorrectText() {
        val document = parseMarkdown(
            "[Lorem ipsum](https://en.wikipedia.org/wiki/Lorem_ipsum) dolor sit amet"
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text).isEqualTo("Lorem ipsum dolor sit amet")
    }

    @Test
    @DisplayName(
        "GIVEN text with a link, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with correct link annotation"
    )
    fun linkShouldReturnTextWithLinkAnnotation() {
        val document = parseMarkdown(
            "[Lorem ipsum](https://en.wikipedia.org/wiki/Lorem_ipsum) dolor sit amet"
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations())
            .singleElement()
            .run {
                extracting { it.start }.isEqualTo(0)
                extracting { it.end }.isEqualTo(11)
                extracting { it.item }
                    .isInstanceOfSatisfying(LinkAnnotation.Url::class.java) { annotation ->
                        assertThat(annotation)
                            .extracting { it.url }
                            .isEqualTo("https://en.wikipedia.org/wiki/Lorem_ipsum")
                    }
            }
    }

    @Test
    @DisplayName(
        "GIVEN text with a link, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with correct link annotation"
    )
    fun linkShouldReturnTextWithLinkAnnotationWithCorrectStyles() {
        val document = parseMarkdown(
            "[Lorem ipsum](https://en.wikipedia.org/wiki/Lorem_ipsum) dolor sit amet"
        )
        val linkStyle = SpanStyle(textDecoration = TextDecoration.Underline)
        val linkStyles = TextLinkStyles(style = linkStyle)
        whenever(typography.link) doReturn linkStyles

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations())
            .singleElement()
            .extracting { it.item }
            .isInstanceOfSatisfying(LinkAnnotation.Url::class.java) { annotation ->
                assertThat(annotation)
                    .extracting { it.styles }
                    .isEqualTo(linkStyles)
            }
    }

    @Test
    @DisplayName(
        "GIVEN text with a link, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with link handled by link handler"
    )
    fun linkShouldReturnTextWithLinkAnnotationHandledByLinkHandler() {
        val document = parseMarkdown(
            "[Lorem ipsum](https://en.wikipedia.org/wiki/Lorem_ipsum) dolor sit amet"
        )

        document.accept(classUnderTest)
        val annotatedString = annotatedStringBuilder.toAnnotatedString()
        val linkAnnotation = annotatedString.getLinkAnnotations(0, annotatedString.length)
            .single()
            .item
        linkAnnotation.linkInteractionListener?.onClick(linkAnnotation)

        verify(linkHandler).handle("https://en.wikipedia.org/wiki/Lorem_ipsum")
        verifyNoMoreInteractions(linkHandler)
    }

    @Test
    @DisplayName(
        "GIVEN text with a link, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without styles"
    )
    fun linkShouldReturnTextWithoutStyles() {
        val document = parseMarkdown(
            "[Lorem ipsum](https://en.wikipedia.org/wiki/Lorem_ipsum) dolor sit amet"
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with soft line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the text without line break"
    )
    fun softLineBreakShouldReturnCorrectText() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text)
            .isEqualTo("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
    }

    @Test
    @DisplayName(
        "GIVEN text with soft line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun softLineBreakShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with soft line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without styles"
    )
    fun softLineBreakShouldReturnTextWithoutStyles() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with hard line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString with the text with line break"
    )
    fun hardLineBreakShouldReturnCorrectText() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,\
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.text)
            .isEqualTo("Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit")
    }

    @Test
    @DisplayName(
        "GIVEN text with hard line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without annotations"
    )
    fun hardLineBreakShouldReturnTextWithoutAnnotations() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,\
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getAnnotations()).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN text with hard line break, " +
            "WHEN visit parsed document, " +
            "THEN return AnnotatedString without styles"
    )
    fun hardLineBreakShouldReturnTextWithoutStyles() {
        val document = parseMarkdown(
            """
                Lorem ipsum dolor sit amet,\
                consectetur adipiscing elit
            """.trimIndent()
        )

        document.accept(classUnderTest)
        val result = annotatedStringBuilder.toAnnotatedString()

        assertThat(result.getStyles()).isEmpty()
    }

    companion object {

        private val parser = Parser.builder().build()

        private fun parseMarkdown(markdown: String): Node =
            parser.parse(markdown)

        private fun AnnotatedString.getStyles(): List<AnnotatedString.Range<out Any>> =
            spanStyles + paragraphStyles

        private fun AnnotatedString.getAnnotations(): List<AnnotatedString.Range<out Any>> =
            sequenceOf(
                getLinkAnnotations(0, length),
                getStringAnnotations(0, length),
                getTtsAnnotations(0, length)
            ).flatten().toList()
    }
}
