package day10

import java.io.FileInputStream

internal enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

private val Direction.reverse: Direction
    get() = when (this) {
        Direction.NORTH -> Direction.SOUTH
        Direction.SOUTH -> Direction.NORTH
        Direction.EAST -> Direction.WEST
        Direction.WEST -> Direction.EAST
    }

internal enum class Pipe(val directions: Collection<Direction>) {
    VERTICAL(setOf(Direction.NORTH, Direction.SOUTH)),
    HORIZONTAL(setOf(Direction.EAST, Direction.WEST)),
    NORTH_EAST(setOf(Direction.NORTH, Direction.EAST)),
    NORTH_WEST(setOf(Direction.NORTH, Direction.WEST)),
    SOUTH_EAST(setOf(Direction.SOUTH, Direction.EAST)),

    SOUTH_WEST(setOf(Direction.SOUTH, Direction.WEST));

    fun anotherEnd(direction: Direction) = directions.first { it != direction }
}

internal typealias Coordinate = Pair<Int, Int>

internal operator fun Coordinate.plus(direction: Direction): Coordinate = when (direction) {
    Direction.NORTH -> x to (y - 1)
    Direction.SOUTH -> x to (y + 1)
    Direction.EAST -> (x + 1) to y
    Direction.WEST -> (x - 1) to y
}

internal val Coordinate.x: Int get() = first
internal val Coordinate.y: Int get() = second

fun main() {
    val lines = FileInputStream("input/day10/input.txt")
        .bufferedReader()
        .readLines()

    val startPosition = startPosition(lines)
    val pipesMap = pipesMap(lines)

    var (first, second) = neighbors(startPosition)
        .filter { (position, direction) -> pipesMap.hasConnection(position, direction) }

    var count = 1
    while (first.first != second.first) {
        first = pipesMap.nextPipe(first)
        second = pipesMap.nextPipe(second)
        count++
    }

    println(count)
}

internal fun Map<Coordinate, Pipe>.nextPipe(pair: Pair<Coordinate, Direction>): Pair<Coordinate, Direction> {
    val (position, direction) = pair
    val pipe = checkNotNull(this[position])

    val anotherEnd = pipe.anotherEnd(direction.reverse)
    return (position + anotherEnd) to anotherEnd
}

internal fun Map<Coordinate, Pipe>.hasConnection(position: Coordinate, direction: Direction) =
    direction.reverse in (this[position]?.directions ?: setOf())

internal fun neighbors(startPosition: Coordinate): List<Pair<Coordinate, Direction>> = listOf(
    ((startPosition.x - 1) to startPosition.y) to Direction.WEST,
    ((startPosition.x + 1) to startPosition.y) to Direction.EAST,
    (startPosition.x to (startPosition.y - 1)) to Direction.NORTH,
    (startPosition.x to (startPosition.y + 1)) to Direction.SOUTH,
)

internal fun pipesMap(lines: List<String>): Map<Coordinate, Pipe> {
    val pipesMap = mutableMapOf<Coordinate, Pipe>()

    lines.forEachIndexed { y, line ->
        line.toList().forEachIndexed { x, symbol ->
            when (symbol) {
                '|' -> pipesMap[x to y] = Pipe.VERTICAL
                '-' -> pipesMap[x to y] = Pipe.HORIZONTAL
                'L' -> pipesMap[x to y] = Pipe.NORTH_EAST
                'J' -> pipesMap[x to y] = Pipe.NORTH_WEST
                '7' -> pipesMap[x to y] = Pipe.SOUTH_WEST
                'F' -> pipesMap[x to y] = Pipe.SOUTH_EAST
            }
        }
    }
    return pipesMap
}

internal fun startPosition(lines: List<String>): Coordinate {
    val y = lines.indexOfFirst { it.contains('S') }

    return lines[y].indexOf('S') to y
}
