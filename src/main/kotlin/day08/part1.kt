package day08

import java.io.FileInputStream


fun main() {
    val map = FileInputStream("input/day08/input.txt").readMap()

    println(map.stepsCount("AAA") { it == "ZZZ" })
}