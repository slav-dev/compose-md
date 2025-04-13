package dev.slav.composemd

import androidx.compose.runtime.staticCompositionLocalOf
import dev.slav.composemd.plugin.BlockQuotePlugin
import dev.slav.composemd.plugin.BulletListPlugin
import dev.slav.composemd.plugin.CodeBlockPlugin
import dev.slav.composemd.plugin.HeadingPlugin
import dev.slav.composemd.plugin.HtmlBlockPlugin
import dev.slav.composemd.plugin.ListItemPlugin
import dev.slav.composemd.plugin.MdPlugin
import dev.slav.composemd.plugin.OrderedListPlugin
import dev.slav.composemd.plugin.ParagraphPlugin
import dev.slav.composemd.plugin.ThematicBreakPlugin
import dev.slav.composemd.ui.component.MdComponent
import dev.slav.composemd.ui.component.MdComponentsVisitor
import org.commonmark.node.Node
import org.commonmark.parser.Parser

/**
 * Compose.md engine.
 */
class ComposeMd private constructor(
    private val parser: Parser,
    private val plugins: List<MdPlugin<*>>
) {

    /**
     * Parse given Markdown text.
     *
     * @param markdown The Markdown text to be parsed.
     *
     * @return Root Markdown node.
     */
    fun parse(markdown: String): Node =
        parser.parse(markdown)

    /**
     * Create components rendering Markdown text.
     *
     * @param markdown The Markdown text to be rendered.
     *
     * @return List of Markdown components.
     */
    fun createComponents(markdown: String): List<MdComponent<*>> =
        createComponents(parse(markdown))

    /**
     * Create components rendering Markdown text.
     *
     * @param node Root Markdown node to be rendered.
     *
     * @return List of Markdown components.
     */
    fun createComponents(node: Node): List<MdComponent<*>> {
        val visitor = MdComponentsVisitor(composeMd = this)
        node.accept(visitor)
        return visitor.components
    }

    /**
     * Create a single component rendering the given Markdown node.
     *
     * If the given node is not supported, this method returns `null`.
     *
     * @param node Markdown node to be rendered.
     *
     * @return A single Markdown component or `null`.
     */
    fun createComponent(node: Node): MdComponent<*>? {
        return plugins
            .firstOrNull { plugin -> plugin.accepts(node) }
            ?.create(node)
    }

    /**
     * Compose.md engine builder.
     */
    class Builder internal constructor(
        private val defaultPlugins: List<MdPlugin<*>>
    ) {

        private val plugins = mutableListOf<MdPlugin<*>>()

        /**
         * Add plugin to the Compose.md engine.
         *
         * @param plugin The plugin to be added.
         *
         * @return This builder.
         */
        fun addPlugin(plugin: MdPlugin<*>): Builder {
            plugins += plugin
            return this
        }

        /**
         * Build a new Compose.md engine.
         *
         * @return New Compose.md engine.
         */
        fun build(): ComposeMd {
            val allPlugins = plugins.toList() + defaultPlugins

            val parser: Parser = Parser.builder()
                .extensions(allPlugins.flatMap { plugin -> plugin.extensions() })
                .build()

            return ComposeMd(
                parser = parser,
                plugins = allPlugins
            )
        }
    }

    companion object {

        /**
         * Create a new Compose.md engine builder.
         *
         * @return New Compose.md engine builder.
         */
        fun builder(): Builder = Builder(
            defaultPlugins = listOf(
                ParagraphPlugin(),
                HeadingPlugin(),
                BulletListPlugin(),
                OrderedListPlugin(),
                ListItemPlugin(),
                BlockQuotePlugin(),
                CodeBlockPlugin(),
                HtmlBlockPlugin(),
                ThematicBreakPlugin()
            )
        )
    }
}

/**
 * Provides Compose.md engine used to parse and render Markdown text.
 */
val LocalComposeMd = staticCompositionLocalOf { ComposeMd.builder().build() }
