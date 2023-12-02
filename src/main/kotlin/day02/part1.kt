package day02

import java.io.FileInputStream

private fun Game.hasValidSets(): Boolean = sets.all { set ->
    val colorsCount = set.groupingBy { it.color }
        .fold(0) { acc, cube -> acc + cube.count }

    (colorsCount[Color.RED] ?: 0) <= 12 &&
            (colorsCount[Color.GREEN] ?: 0) <= 13 &&
            (colorsCount[Color.BLUE] ?: 0) <= 14
}

fun main() {
    val sum = FileInputStream("input/day02/input.txt")
        .bufferedReader()
        .lineSequence()
        .map { it.parseGame() }
        .filter { it.hasValidSets() }
        .map { it.gameNumber }
        .sum()
    println(sum)
}