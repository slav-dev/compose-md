package dev.slav.composemd.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import dev.slav.composemd.ComposeMd
import dev.slav.composemd.LocalComposeMd
import dev.slav.composemd.link.LocalMdLinkHandler
import dev.slav.composemd.link.MdLinkHandler
import dev.slav.composemd.link.ViewActionLinkHandler
import dev.slav.composemd.ui.style.LocalMdIndent
import dev.slav.composemd.ui.style.LocalMdListItemWidth
import dev.slav.composemd.ui.style.LocalMdSpacing
import dev.slav.composemd.ui.style.LocalMdTypography
import dev.slav.composemd.ui.style.MdTypography
import dev.slav.composemd.ui.style.materialMdTypography

/**
 * Wrapper for composable Markdown components,
 * applying given configuration to the contents.
 *
 * @param composeMd Compose.md engine to be used for parsing
 * and creating Markdown components.
 * @param typography Markdown typography styles to be applied
 * to Markdown components.
 * @param spacing Spacing between subsequent Markdown components.
 * @param indent Indentation of Markdown components.
 * @param linkHandler Markdown link URL handler.
 * @param content Content of the wrapper.
 */
@Composable
fun MdConfig(
    composeMd: ComposeMd = LocalComposeMd.current,
    typography: MdTypography = materialMdTypography(),
    spacing: Dp = LocalMdSpacing.current,
    indent: Dp = LocalMdIndent.current,
    listItemWidth: Dp = LocalMdListItemWidth.current,
    linkHandler: MdLinkHandler = ViewActionLinkHandler(context = LocalContext.current),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalComposeMd provides composeMd,
        LocalMdTypography provides typography,
        LocalMdSpacing provides spacing,
        LocalMdIndent provides indent,
        LocalMdListItemWidth provides listItemWidth,
        LocalMdLinkHandler provides linkHandler,
        content = content
    )
}
