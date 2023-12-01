package day01

import java.io.FileInputStream
import java.io.InputStream


fun main() {
    val sum = solve(FileInputStream("input/day01/input.txt"))

    println(sum)
}

private fun solve(inputStream: InputStream): Int {

    val sum = inputStream.bufferedReader()
        .lineSequence()
        .map { line ->
            val first = line.firstDigit() * 10

            val last = line.lastDigit().toInt()

            first + last
        }.sum()
    return sum
}

private fun String.lastDigit() = last { ('0'..'9').contains(it) }.toString()

private fun String.firstDigit() = first { ('0'..'9').contains(it) }.toString().toInt()