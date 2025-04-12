package dev.slav.composemd.ui.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineBreak

/**
 * Markdown typography styles.
 *
 * @property heading1 Heading 1.
 * @property heading2 Heading 2.
 * @property heading3 Heading 3.
 * @property heading4 Heading 4.
 * @property heading5 Heading 5.
 * @property heading6 Heading 6.
 * @property paragraph Paragraph.
 * @property code Code.
 * @property link Link.
 * @property emphasis Emphasis.
 * @property strongEmphasis Strong emphasis.
 */
data class MdTypography(
    val heading1: TextStyle = MdTypographyDefaults.heading1,
    val heading2: TextStyle = MdTypographyDefaults.heading2,
    val heading3: TextStyle = MdTypographyDefaults.heading3,
    val heading4: TextStyle = MdTypographyDefaults.heading4,
    val heading5: TextStyle = MdTypographyDefaults.heading5,
    val heading6: TextStyle = MdTypographyDefaults.heading6,
    val paragraph: TextStyle = MdTypographyDefaults.paragraph,
    val code: TextStyle = MdTypographyDefaults.code,
    val link: TextLinkStyles = MdTypographyDefaults.link,
    val emphasis: SpanStyle = MdTypographyDefaults.emphasis,
    val strongEmphasis: SpanStyle = MdTypographyDefaults.strongEmphasis
)

/**
 * Create Material Design 3 typography styles.
 *
 * @param heading1 Heading 1.
 * @param heading2 Heading 2.
 * @param heading3 Heading 3.
 * @param heading4 Heading 4.
 * @param heading5 Heading 5.
 * @param heading6 Heading 6.
 * @param paragraph Paragraph.
 * @param code Code.
 * @param link Link.
 * @param emphasis Emphasis.
 * @param strongEmphasis Strong emphasis.
 *
 * @return New Material Design 3 typography styles.
 */
@Composable
fun materialMdTypography(
    heading1: TextStyle = MaterialTheme.typography.headlineLarge.withHeadingLineBreaks(),
    heading2: TextStyle = MaterialTheme.typography.headlineMedium.withHeadingLineBreaks(),
    heading3: TextStyle = MaterialTheme.typography.headlineSmall.withHeadingLineBreaks(),
    heading4: TextStyle = MaterialTheme.typography.titleLarge.withHeadingLineBreaks(),
    heading5: TextStyle = MaterialTheme.typography.titleMedium.withHeadingLineBreaks(),
    heading6: TextStyle = MaterialTheme.typography.titleSmall.withHeadingLineBreaks(),
    paragraph: TextStyle = MaterialTheme.typography.bodyMedium.withParagraphLineBreaks(),
    code: TextStyle = paragraph.copy(fontFamily = FontFamily.Monospace),
    link: TextLinkStyles = MdTypographyDefaults.link.withColor(MaterialTheme.colorScheme.primary),
    emphasis: SpanStyle = MdTypographyDefaults.emphasis,
    strongEmphasis: SpanStyle = MdTypographyDefaults.strongEmphasis
): MdTypography =
    MdTypography(
        heading1 = heading1,
        heading2 = heading2,
        heading3 = heading3,
        heading4 = heading4,
        heading5 = heading5,
        heading6 = heading6,
        paragraph = paragraph,
        code = code,
        link = link,
        emphasis = emphasis,
        strongEmphasis = strongEmphasis
    )

private fun TextStyle.withHeadingLineBreaks(): TextStyle =
    copy(lineBreak = LineBreak.Heading)

private fun TextStyle.withParagraphLineBreaks(): TextStyle =
    copy(lineBreak = LineBreak.Paragraph)

private fun TextLinkStyles.withColor(color: Color): TextLinkStyles =
    TextLinkStyles(
        style = this.style?.copy(color = color),
        focusedStyle = this.focusedStyle?.copy(color = color),
        hoveredStyle = this.hoveredStyle?.copy(color = color),
        pressedStyle = this.pressedStyle?.copy(color = color)
    )

/**
 * Provides Markdown typography styles.
 */
val LocalMdTypography = staticCompositionLocalOf { MdTypography() }
