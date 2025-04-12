package dev.slav.composemd.ui.style

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val DefaultMdSpacing = 8.dp
private val DefaultMdIndent = 12.dp
private val DefaultMdListItemWidth = 24.dp

/**
 * Provides spacing between subsequent Markdown components.
 */
val LocalMdSpacing = staticCompositionLocalOf { DefaultMdSpacing }

/**
 * Provides indentation of Markdown components.
 */
val LocalMdIndent = staticCompositionLocalOf { DefaultMdIndent }

/**
 * Provides width of Markdown list item indicator.
 */
val LocalMdListItemWidth = staticCompositionLocalOf { DefaultMdListItemWidth }
