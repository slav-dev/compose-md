package dev.slav.composemd.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import dev.slav.composemd.R
import dev.slav.composemd.ui.style.LocalMdTypography

/**
 * Bullet list item indicator.
 *
 * @param modifier Modifier to be applied to this indicator.
 */
@Composable
fun MdBulletListItemIndicator(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalMdTypography.current.paragraph
) {
    Text(
        text = stringResource(R.string.dev_slav_compose_md_bullet_list_item_indicator),
        modifier = modifier,
        overflow = TextOverflow.Visible,
        maxLines = 1,
        style = style
    )
}
