package day08

import java.io.InputStream

internal data class Node(val left: String, val right: String)

internal data class Document(val instructions: Collection<Char>, val network: Map<String, Node>)

internal fun Document.stepsCount(from: String, predicate: (String) -> Boolean): Int {
    var currentNode = from

    return instructionSequence(instructions)
        .indexOfFirst { instruction ->
            predicate(currentNode).also {
                currentNode = network.move(currentNode, instruction)
            }
        }
}

internal fun InputStream.readMap(): Document {
    val lines = bufferedReader().readLines()

    val instructions = lines.first().toList()

    val network = lines.drop(2).associate {
        val (from, node) = it.split(" = ", limit = 2)

        from to node.toNode()
    }
    return Document(instructions, network)
}

private fun Map<String, Node>.move(currentNode: String, instruction: Char): String {
    val node = checkNotNull(this[currentNode])

    return when (instruction) {
        'L' -> node.left
        'R' -> node.right
        else -> throw IllegalStateException("Wrong instruction '$instruction'")
    }
}

private fun instructionSequence(instructions: Collection<Char>) = sequence { while (true) yieldAll(instructions) }

private fun String.toNode(): Node = removeSurrounding("(", ")")
    .split(", ", limit = 2)
    .let { (left, right) -> Node(left, right) }