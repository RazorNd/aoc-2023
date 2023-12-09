package day09

import java.io.InputStream

internal fun makeSubSequences(sequence: List<Int>): List<List<Int>> {
    val subSequences = mutableListOf(sequence)

    do {
        val next = subSequences.last().windowed(2).map { (a, b) -> b - a }
        subSequences.add(next)
    } while (next.any { it != 0 })

    return subSequences
}

internal fun InputStream.readSequences() = bufferedReader()
    .readLines()
    .map { line -> line.split(" ").map { it.toInt() } }