package day10

import java.io.FileInputStream

fun main() {
    val lines = FileInputStream("input/day10/input.txt")
        .bufferedReader()
        .readLines()

    val pipesMap = pipesMap(lines)
    val startPosition = startPosition(lines)

    var (first, second) = neighbors(startPosition)
        .filter { (position, direction) -> pipesMap.hasConnection(position, direction) }

    val path = mutableSetOf(startPosition, first.first, second.first)

    while (first.first != second.first) {
        first = pipesMap.nextPipe(first)
        second = pipesMap.nextPipe(second)
        path.add(first.first)
        path.add(second.first)
    }

    var area = 0
    for (y in lines.indices) {
        var windings = 0
        var start = 0
        for (x in lines[y].indices) {
            val point = x to y
            if (point in path) continue

            for (xi in start..<x) {
                val test = xi to y
                if (pipesMap[test] in setOf(Pipe.VERTICAL, Pipe.SOUTH_EAST, Pipe.SOUTH_WEST))
                    windings++
            }
            start = x

            if (windings % 2 == 1) area++
        }
    }

    println(area)

}

private fun Map<Coordinate, Pipe>.path(
    pairs: Collection<Pair<Coordinate, Direction>>,
    action: (Coordinate, Direction, Pipe) -> Unit
) {
//    val result = mutableListOf<Pair<Coordinate, Direction>>()
    for ((coordinate, direction) in pairs) {
        val pipe = this[coordinate] ?: continue
        if (direction in pipe.directions) {
            action(coordinate, direction, pipe)
        }
    }
//    return result
}