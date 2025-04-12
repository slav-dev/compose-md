package dev.slav.composemd.node

import org.commonmark.node.Node

/**
 * Return a sequence of children of this node.
 *
 * @return Sequence of children of this node.
 */
fun Node.childrenSequence(): Sequence<Node> =
    generateSequence(firstChild) { child -> child.next }

/**
 * Return a sequence of ancestors of this node, starting from this node.
 *
 * @return Sequence of ancestors of this node.
 */
fun Node.ancestorsSequence(): Sequence<Node> =
    generateSequence(this) { node -> node.parent }
