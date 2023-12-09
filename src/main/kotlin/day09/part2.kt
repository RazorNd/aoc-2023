package day09

import java.io.FileInputStream

fun main() {
    val sum = FileInputStream("input/day09/input.txt")
        .readSequences()
        .sumOf { predictNextValue(it) }

    println(sum)
}

private fun predictNextValue(sequence: List<Int>): Int {
    return makeSubSequences(sequence)
        .map { it.first() }
        .reversed()
        .reduce { a, i -> i - a }
}