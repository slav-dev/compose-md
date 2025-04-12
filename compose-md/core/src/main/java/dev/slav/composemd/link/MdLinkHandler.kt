package dev.slav.composemd.link

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Markdown link URL handler.
 */
fun interface MdLinkHandler {

    /**
     * Handle given link URL or label.
     *
     * @param url The link URL to be handled.
     */
    fun handle(url: String)
}

/**
 * Provides Markdown link URL handler.
 */
val LocalMdLinkHandler = staticCompositionLocalOf { MdLinkHandler {} }
