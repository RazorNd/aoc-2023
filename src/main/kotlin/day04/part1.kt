package day04

import java.io.FileInputStream
import kotlin.math.pow

fun main() {
    val sum = parseCards(FileInputStream("input/day04/input.txt"))
        .map { it.matchingNumber }
        .map { if (it > 0) 2.0.pow(it - 1) else 0.0 }
        .sum()
        .toInt()

    println(sum)
}