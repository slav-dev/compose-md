package dev.slav.composemd.ui.component

import dev.slav.composemd.ComposeMd
import org.assertj.core.api.Assertions.assertThat
import org.commonmark.node.BlockQuote
import org.commonmark.node.BulletList
import org.commonmark.node.CustomBlock
import org.commonmark.node.CustomNode
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.Heading
import org.commonmark.node.HtmlBlock
import org.commonmark.node.Image
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.LinkReferenceDefinition
import org.commonmark.node.ListItem
import org.commonmark.node.Node
import org.commonmark.node.OrderedList
import org.commonmark.node.Paragraph
import org.commonmark.node.ThematicBreak
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@DisplayName("MdComponentsVisitor: supported nodes")
@ExtendWith(MockitoExtension::class)
class MdComponentsVisitorSupportedNodesTest {

    @Mock
    lateinit var composeMd: ComposeMd

    @InjectMocks
    lateinit var classUnderTest: MdComponentsVisitor

    @Mock
    lateinit var component: MdComponent<Node>

    @BeforeEach
    fun initVisitor() {
        whenever(composeMd.createComponent(any())) doReturn component
    }

    @ParameterizedTest(
        name = "GIVEN $ARGUMENTS_PLACEHOLDER, " +
            "WHEN it accepts the visitor, " +
            "THEN a single component should be created"
    )
    @MethodSource("provideSupportedNodes")
    fun createdComponent(node: Node) {
        node.accept(classUnderTest)
        val result = classUnderTest.components

        assertThat(result)
            .singleElement()
            .isEqualTo(component)
    }

    @ParameterizedTest(
        name = "GIVEN $ARGUMENTS_PLACEHOLDER, " +
            "WHEN it accepts the visitor, " +
            "THEN a component should be created for that $ARGUMENTS_PLACEHOLDER"
    )
    @MethodSource("provideSupportedNodes")
    fun createdForNodeByMatchingPlugin(node: Node) {
        node.accept(classUnderTest)

        verify(composeMd, times(1)).createComponent(node)
    }

    companion object {

        @JvmStatic
        fun provideSupportedNodes(): Iterable<Arguments> =
            sequenceOf(
                Arguments.of(object : Paragraph() {
                    override fun toString(): String = "a paragraph"
                }),
                Arguments.of(object : Heading() {
                    override fun toString(): String = "a heading"
                }),
                Arguments.of(object : BulletList() {
                    override fun toString(): String = "a bullet list"
                }),
                Arguments.of(object : OrderedList() {
                    override fun toString(): String = "an ordered list"
                }),
                Arguments.of(object : ListItem() {
                    override fun toString(): String = "a list item"
                }),
                Arguments.of(object : BlockQuote() {
                    override fun toString(): String = "a block quote"
                }),
                Arguments.of(object : Image() {
                    override fun toString(): String = "an image"
                }),
                Arguments.of(object : FencedCodeBlock() {
                    override fun toString(): String = "a fenced code block"
                }),
                Arguments.of(object : IndentedCodeBlock() {
                    override fun toString(): String = "an indented code block"
                }),
                Arguments.of(object : HtmlBlock() {
                    override fun toString(): String = "a HTML block"
                }),
                Arguments.of(object : ThematicBreak() {
                    override fun toString(): String = "a thematic break"
                }),
                Arguments.of(object : CustomNode() {
                    override fun toString(): String = "a custom node"
                }),
                Arguments.of(object : CustomBlock() {
                    override fun toString(): String = "a custom block"
                }),
                Arguments.of(object : LinkReferenceDefinition() {
                    override fun toString(): String = "a link reference definition"
                })
            ).asIterable()
    }
}
