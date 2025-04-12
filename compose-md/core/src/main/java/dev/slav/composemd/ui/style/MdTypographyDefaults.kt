package dev.slav.composemd.ui.style

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

internal object MdTypographyDefaults {

    val heading1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineBreak = LineBreak.Heading
    )

    val heading2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineBreak = LineBreak.Heading
    )

    val heading3: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineBreak = LineBreak.Heading
    )

    val heading4: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineBreak = LineBreak.Heading
    )

    val heading5: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineBreak = LineBreak.Heading
    )

    val heading6: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineBreak = LineBreak.Heading
    )

    val paragraph: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        lineBreak = LineBreak.Paragraph
    )

    val code: TextStyle = paragraph.copy(fontFamily = FontFamily.Monospace)

    val link: TextLinkStyles = TextLinkStyles(
        style = SpanStyle(textDecoration = TextDecoration.Underline)
    )

    val emphasis: SpanStyle = SpanStyle(fontStyle = FontStyle.Italic)

    val strongEmphasis: SpanStyle = SpanStyle(fontWeight = FontWeight.Bold)
}
