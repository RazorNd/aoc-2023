package day01

import java.io.FileInputStream
import java.io.InputStream

fun main() {
    println(solve(FileInputStream("input/day01/input.txt")))
}

private fun solve(inputStream: InputStream): Number {
    val digits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9
    )

    return inputStream.bufferedReader()
        .lineSequence()
        .map { line ->
            val first = line.firstValueByKey(digits)
            val last = line.lastValueByKey(digits)

            first * 10 + last
        }
        .sum()
}

private fun String.firstValueByKey(digits: Map<String, Int>) =
    digits.entries.map { it.value to indexOf(it.key) }
        .filter { it.second != -1 }
        .minBy { it.second }.first

private fun String.lastValueByKey(digits: Map<String, Int>) =
    digits.entries.map { it.value to lastIndexOf(it.key) }
        .filter { it.second != -1 }
        .maxBy { it.second }.first

