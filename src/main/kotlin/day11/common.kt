package day11

import kotlin.math.abs

private data class Point(val x: Int, val y: Int)

internal fun calcDistances(lines: List<String>, multiplayer: Int = 1): Long {
    val yRange = lines.indices
    val xRange = lines.first().indices

    val spaceMap = lines
        .withIndex()
        .flatMap { (y, line) ->
            line.withIndex().filter { (_, v) -> v == '#' }.map { (x) -> Point(x, y) }
        }

    val emptyColum = xRange.filter { spaceMap.column(it).isEmpty() }
    val emptyRow = yRange.filter { spaceMap.row(it).isEmpty() }

    val extendedSpace =
        spaceMap.map { (x, y) ->
            Point(
                emptyColum.count { it < x } * multiplayer + x,
                emptyRow.count { it < y } * multiplayer + y
            )
        }

    return extendedSpace
        .withIndex()
        .flatMap { (i, a) -> extendedSpace.drop(i + 1).map { b -> (a distanceTo b).toLong() } }
        .sum()
}

private infix fun Point.distanceTo(other: Point): Int = abs(other.x - x) + abs(other.y - y)
private fun List<Point>.row(y: Int): List<Point> = filter { it.y == y }
private fun List<Point>.column(x: Int): List<Point> = filter { it.x == x }