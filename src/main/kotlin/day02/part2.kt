package day02

import java.io.FileInputStream
import kotlin.math.max

fun main() {
    val sum = FileInputStream("input/day02/input.txt")
        .bufferedReader()
        .lineSequence()
        .map { it.parseGame() }
        .map { it.calcPower() }
        .sum()

    println(sum)
}

private fun Game.calcPower(): Int {
    val colorsNeed = sets.flatten()
        .groupingBy { it.color }
        .fold(0) { acc, cube -> max(acc, cube.count) }

    return (colorsNeed[Color.RED] ?: 0) * (colorsNeed[Color.GREEN] ?: 0) * (colorsNeed[Color.BLUE] ?: 0)

}