package day12

import java.io.FileInputStream

fun main() {
    val sum = FileInputStream("input/day12/input.txt")
        .bufferedReader()
        .readLines()
        .sumOf { line ->
            val (records, groups) = line.split(" ", limit = 2)

            arrangements(records, groups.split(",").map { it.toInt() })
        }

    println(sum)
}