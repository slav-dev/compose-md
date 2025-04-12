package dev.slav.composemd.ui.text

/**
 * HTML string builder.
 */
class HtmlBuilder {

    private val htmlStack = mutableListOf<String>()

    private val htmlStringBuilder = StringBuilder()

    /**
     * Flag indicating whether the builder is currently building HTML.
     */
    val isBuilding: Boolean
        get() = htmlStack.isNotEmpty()

    /**
     * Flag indicating whether the builder has completed building HTML.
     */
    val isCompleted: Boolean
        get() = htmlStack.isEmpty()

    /**
     * Append given tag to the HTML.
     *
     * @param tag HTML tag.
     */
    fun appendTag(tag: String) {
        if (isOpeningTag(tag)) {
            htmlStack += tag
        }
        if (isClosingTag(tag) && htmlStack.isNotEmpty()) {
            htmlStack.removeAt(htmlStack.lastIndex)
        }
        htmlStringBuilder.append(tag)
    }

    private fun isOpeningTag(tag: String): Boolean =
        !tag.startsWith("</")

    private fun isClosingTag(tag: String): Boolean =
        tag.startsWith("</") || tag.endsWith("/>")

    /**
     * Append given text to the HTML.
     *
     * @param text Text to be appended.
     */
    fun appendText(text: String) {
        htmlStringBuilder.append(text)
    }

    /**
     * Build HTML string.
     *
     * @return Resulting HTML string.
     */
    fun build(): String {
        val result = htmlStringBuilder.toString()
        htmlStack.clear()
        htmlStringBuilder.clear()
        return result
    }
}
