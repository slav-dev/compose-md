package dev.slav.composemd.node

import org.assertj.core.api.Assertions.assertThat
import org.commonmark.node.Document
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.Heading
import org.commonmark.node.Paragraph
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Node::childrenSequence")
class ChildrenSequenceTest {

    @Test
    @DisplayName(
        "GIVEN a node with children, " +
            "WHEN I get a children sequence, " +
            "THEN all children should be present in order"
    )
    fun shouldContainAllChildren() {
        val parent = Document()
        val children = listOf(
            Heading(),
            Paragraph(),
            FencedCodeBlock(),
            Paragraph()
        ).onEach { child ->
            parent.appendChild(child)
        }

        val result = parent.childrenSequence()

        assertThat(result.asIterable())
            .containsExactly(*children.toTypedArray())
    }
}
