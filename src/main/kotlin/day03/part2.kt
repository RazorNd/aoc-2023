package day03

import java.io.FileInputStream

fun main() {
    val input = FileInputStream("input/day03/input.txt").bufferedReader().readText()

    val numbers = parseNumbers(input)

    input.lineSequence()
        .withIndex()
        .flatMap { (y, line) -> line.withIndex().filter { it.value == '*' }.map { Position(it.index, y) } }
        .map { position -> numbers.filter { num -> num.adjacentPositions.any { it == position } } }
        .filter { it.size == 2 }
        .sumOf { gears -> gears.map { it.value }.fold(1) { a, b -> a * b }.toInt() }
        .also { println(it) }
}