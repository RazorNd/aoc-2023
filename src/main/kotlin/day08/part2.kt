package day08

import java.io.FileInputStream

private tailrec fun gcd(x: Long, y: Long): Long = if (x > 0) gcd(y % x, x) else y

fun main() {
    val map = FileInputStream("input/day08/input.txt").readMap()

    val steps = map.network.keys
        .filter { it.endsWith("A") }
        .map { node -> map.stepsCount(node) { it.endsWith("Z") }.toLong() }
        .reduce { acc, el -> (acc * el) / gcd(acc, el) }

    println(steps)
}