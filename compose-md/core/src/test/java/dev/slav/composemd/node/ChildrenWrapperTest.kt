package dev.slav.composemd.node

import org.assertj.core.api.Assertions.assertThat
import org.commonmark.node.Document
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.Heading
import org.commonmark.node.Paragraph
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("ChildrenWrapper")
class ChildrenWrapperTest {

    @Test
    @DisplayName(
        "GIVEN a node with children, " +
            "WHEN I create ChildrenWrapper, " +
            "THEN the new node contains all children of the original node"
    )
    fun shouldCopyChildren() {
        val parent = Document()
        val children = listOf(
            Heading(),
            Paragraph(),
            FencedCodeBlock(),
            Paragraph()
        ).onEach { child ->
            parent.appendChild(child)
        }

        val classUnderTest = ChildrenWrapper(source = parent)
        val result = classUnderTest.childrenSequence()

        assertThat(result.asIterable())
            .containsExactly(*children.toTypedArray())
    }
}
