package dev.slav.composemd.link

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Markdown link URL handler opening links in an application
 * allowing for this type of link to be viewed.
 *
 * @param context Context to send an intent from.
 */
class ViewActionLinkHandler(
    private val context: Context
) : MdLinkHandler {

    /**
     * Handle given link URL or label.
     *
     * @param url The link URL to be handled.
     */
    override fun handle(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
