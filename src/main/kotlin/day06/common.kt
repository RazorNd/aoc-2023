package day06

import kotlin.math.sqrt

internal fun calcWaysCount(time: Long, distance: Long): Long {
    // 71530
    // 940200
    // D =
    val d = (time * time - 4 * distance).toDouble()

    val x1 = (time - sqrt(d)) / 2 + 0.1
    val x2 = (time + sqrt(d)) / 2 - 0.1

    return x2.toLong() - x1.toLong()
}