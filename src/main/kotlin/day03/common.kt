package day03

internal data class NumberPosition(val value: Int, val xStart: Int, val xEnd: Int, val y: Int)

internal data class Position(val x: Int, val y: Int)

internal val NumberPosition.adjacentPositions: Sequence<Position>
    get() = sequence {
        yieldAll(((xStart - 1)..(xEnd + 1)).map { Position(it, y - 1) })
        yieldAll(((xStart - 1)..(xEnd + 1)).map { Position(it, y + 1) })
        yield(Position(xStart - 1, y))
        yield(Position(xEnd + 1, y))
    }

internal fun parseNumbers(input: String): List<NumberPosition> {
    val numbers = mutableListOf<NumberPosition>()
    for ((y, line) in input.lineSequence().withIndex()) {
        var numberStarted = false
        var positionStarted = 0

        for ((x, symbol) in line.withIndex()) {
            if (symbol in '0'..'9' && !numberStarted) {
                numberStarted = true
                positionStarted = x
            }
            if (symbol !in '0'..'9' && numberStarted) {
                val value = line.substring(positionStarted, x).toInt()

                numbers.add(NumberPosition(value, positionStarted, x - 1, y))

                numberStarted = false
            }
        }

        if (numberStarted) {
            val value = line.substring(positionStarted).toInt()
            numbers.add(NumberPosition(value, positionStarted, line.lastIndex, y))
        }
    }
    return numbers
}

