package dev.slav.composemd.node

import org.assertj.core.api.Assertions.assertThat
import org.commonmark.node.Document
import org.commonmark.node.Paragraph
import org.commonmark.node.Text
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Node::ancestorsSequence")
class AncestorsSequenceTest {

    @Test
    @DisplayName(
        "GIVEN a node with ancestors, " +
            "WHEN I get an ancestors sequence, " +
            "THEN all ancestors should be present in reverse order"
    )
    fun shouldContainAllAncestors() {
        val document = Document()
        val paragraph = Paragraph()
        val text = Text("Lorem ipsum dolor sit amet")
        document.appendChild(paragraph)
        paragraph.appendChild(text)

        val result = text.ancestorsSequence()

        assertThat(result.asIterable())
            .containsExactly(text, paragraph, document)
    }
}
