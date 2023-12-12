package day12

import java.io.FileInputStream

fun main() {
    val sum = FileInputStream("input/day12/input.txt")
        .bufferedReader()
        .readLines()
        .sumOf { line ->
            val (records, groups) = line.split(" ", limit = 2)
                .let { (r, g) -> r to g.split(",").map { it.toInt() } }

            arrangements(
                List(5) { records }.joinToString("?"),
                List(5) { groups }.flatten()
            )
        }

    println(sum)
}