package dev.slav.composemd.node

import org.commonmark.node.Document
import org.commonmark.node.Node

/**
 * Special node for wrapping children of another node.
 *
 * This node can be used when creating components multiple nodes
 * if their parent node should be ignored in the process.
 */
class ChildrenWrapper : Document {

    constructor(source: Node) : super() {
        moveChildrenFrom(source)
    }

    private fun moveChildrenFrom(source: Node) {
        val children = source.childrenSequence().toList()
        for (child in children) {
            appendChild(child)
        }
    }
}
