package day11

import java.io.FileInputStream


fun main() {
    val lines = FileInputStream("input/day11/input.txt")
        .bufferedReader()
        .readLines()

    println(calcDistances(lines))
}

