package day03

import java.io.FileInputStream


fun main() {
    val input = FileInputStream("input/day03/input.txt").bufferedReader().readText()

    val numbers = parseNumbers(input)
    val symbols = parseSymbolPositions(input)

    val sum = numbers.filter { number -> number.adjacentPositions.any { it in symbols } }.sumOf { it.value }

    println(sum)
}

private fun parseSymbolPositions(input: String): Set<Position> = input.lineSequence()
    .withIndex()
    .flatMap { (y, line) ->
        line.withIndex()
            .filter { it.value != '.' && it.value !in '0'..'9' }
            .map { Position(it.index, y) }
    }
    .toSet()
