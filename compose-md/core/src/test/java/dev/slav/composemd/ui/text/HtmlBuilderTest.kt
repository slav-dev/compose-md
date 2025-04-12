package dev.slav.composemd.ui.text

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("HtmlBuilder")
class HtmlBuilderTest {

    private val classUnderTest = HtmlBuilder()

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I do nothing, " +
            "THEN it should not be building"
    )
    fun newNotBuilding() {
        val result = classUnderTest.isBuilding

        assertThat(result).isFalse()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I do nothing, " +
            "THEN it should be completed"
    )
    fun newCompleted() {
        val result = classUnderTest.isCompleted

        assertThat(result).isTrue()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I do nothing, " +
            "THEN the resulting string should be empty"
    )
    fun newEmptyString() {
        val result = classUnderTest.build()

        assertThat(result).isEmpty()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag, " +
            "THEN it should be building"
    )
    fun openingTagBuilding() {
        classUnderTest.appendTag("<b>")

        val result = classUnderTest.isBuilding

        assertThat(result).isTrue()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag, " +
            "THEN it should not be completed"
    )
    fun openingTagNotCompleted() {
        classUnderTest.appendTag("<b>")

        val result = classUnderTest.isCompleted

        assertThat(result).isFalse()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag, " +
            "THEN the resulting string should contain the tag"
    )
    fun openingTagStringWithTag() {
        classUnderTest.appendTag("<b>")

        val result = classUnderTest.build()

        assertThat(result).isEqualTo("<b>")
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and some text, " +
            "THEN it should be building"
    )
    fun openingTagAndTextBuilding() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendText("Text")

        val result = classUnderTest.isBuilding

        assertThat(result).isTrue()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and some text, " +
            "THEN it should not be completed"
    )
    fun openingTagAndTextNotCompleted() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendText("Text")

        val result = classUnderTest.isCompleted

        assertThat(result).isFalse()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and some text, " +
            "THEN the resulting string should contain the tag and the text"
    )
    fun openingTagAndTextStringWithTagAndText() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendText("Text")

        val result = classUnderTest.build()

        assertThat(result).isEqualTo("<b>Text")
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and a closing tag, " +
            "THEN it should not be building"
    )
    fun openingTagAndClosingTagNotBuilding() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendTag("</b>")

        val result = classUnderTest.isBuilding

        assertThat(result).isFalse()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and a closing tag, " +
            "THEN it should be completed"
    )
    fun openingTagAndClosingTagCompleted() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendTag("</b>")

        val result = classUnderTest.isCompleted

        assertThat(result).isTrue()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an opening tag and a closing tag, " +
            "THEN the resulting string should contain the opening tag and the closing tag"
    )
    fun openingTagAndClosingTagStringWithOpeningTagAndClosing() {
        classUnderTest.appendTag("<b>")
        classUnderTest.appendTag("</b>")

        val result = classUnderTest.build()

        assertThat(result).isEqualTo("<b></b>")
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an empty tag, " +
            "THEN it should not be building"
    )
    fun emptyTagNotBuilding() {
        classUnderTest.appendTag("<br/>")

        val result = classUnderTest.isBuilding

        assertThat(result).isFalse()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an empty tag, " +
            "THEN it should be completed"
    )
    fun emptyTagCompleted() {
        classUnderTest.appendTag("<br/>")

        val result = classUnderTest.isCompleted

        assertThat(result).isTrue()
    }

    @Test
    @DisplayName(
        "GIVEN HTML string builder, " +
            "WHEN I add an empty tag, " +
            "THEN the resulting string should contain the empty tag"
    )
    fun emptyTagStringWithOpeningTagAndClosing() {
        classUnderTest.appendTag("<br/>")

        val result = classUnderTest.build()

        assertThat(result).isEqualTo("<br/>")
    }
}
