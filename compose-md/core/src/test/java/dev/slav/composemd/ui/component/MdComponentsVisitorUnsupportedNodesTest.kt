package dev.slav.composemd.ui.component

import dev.slav.composemd.ComposeMd
import org.commonmark.node.Document
import org.commonmark.node.Node
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verifyNoInteractions

@DisplayName("MdComponentsVisitor: unsupported nodes")
@ExtendWith(MockitoExtension::class)
class MdComponentsVisitorUnsupportedNodesTest {

    @Mock
    lateinit var composeMd: ComposeMd

    @InjectMocks
    lateinit var classUnderTest: MdComponentsVisitor

    @ParameterizedTest(
        name = "GIVEN $ARGUMENTS_PLACEHOLDER, " +
            "WHEN it accepts the visitor, " +
            "THEN no component is ever created"
    )
    @MethodSource("provideSupportedNodes")
    fun createdForParagraphByMatchingPlugin(node: Node) {
        node.accept(classUnderTest)

        verifyNoInteractions(composeMd)
    }

    companion object {

        @JvmStatic
        fun provideSupportedNodes(): Iterable<Arguments> =
            sequenceOf(
                Arguments.of(object : Document() {
                    override fun toString(): String = "a document"
                })
            ).asIterable()
    }
}
