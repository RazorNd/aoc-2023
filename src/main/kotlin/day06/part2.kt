package day06

import java.io.FileInputStream

fun main() {
    val (time, distance) = FileInputStream("input/day06/input.txt")
        .bufferedReader()
        .readLines()
        .map { line ->
            val (_, value) = line.split(":", limit = 2)

            value.replace(" ", "").toLong()
        }

    println(calcWaysCount(time, distance))
}