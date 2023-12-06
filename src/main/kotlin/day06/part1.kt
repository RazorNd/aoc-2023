package day06

import java.io.FileInputStream
import java.util.regex.Pattern

fun main() {
    val (timesLine, distancesLine) = FileInputStream("input/day06/input.txt")
        .bufferedReader()
        .readLines()
        .map { line ->
            val (_, values) = line.split(":", limit = 2)
            values.trim().split(Pattern.compile("\\s+")).map { it.toLong() }
        }

    val result = timesLine.zip(distancesLine)
        .map { (time, distance) -> calcWaysCount(time, distance) }
        .fold(1L) { a, b -> a * b }

    println(result)
}

