package day05

import java.io.FileInputStream
import kotlin.math.max
import kotlin.math.min

private val MapEntry.sourceEnd: Long get() = sourceStart + length - 1

private val MapEntry.sourceEndExclusive: Long get() = sourceStart + length

fun main() {
    val blocks = parseBlocks(FileInputStream("input/day05/input.txt"))

    val seedsMaps = blocks.first()
        .substring("seeds: ".length)
        .split(" ")
        .map { it.toLong() }
        .windowed(2, 2)
        .map { (destination, length) -> destination..<(destination + length) }

    val maps = parseMaps(blocks.drop(1))

    val location = maps.fold(seedsMaps) { prev, current -> prev.flatMap { current.mapToDestination(it) } }
        .minOf { it.first }

    println(location)
}

private fun List<MapEntry>.mapToDestination(prev: LongRange): Sequence<LongRange> {
    val intersectedEntries = filter { prev intersects it.sourceRange }.sortedBy { it.sourceStart }

    if (intersectedEntries.isEmpty()) {
        return sequenceOf(prev)
    }

    return sequence {
        beforeFirstIntersect(prev, intersectedEntries.first())

        intersectedEntries.forEach { yield(it.mapToDestination(prev)) }

        afterLastIntersect(prev, intersectedEntries.last())
    }
}

private suspend fun SequenceScope<LongRange>.beforeFirstIntersect(prev: LongRange, first: MapEntry) {
    if (prev.first < first.sourceStart) {
        yield(prev.first..<first.sourceStart)
    }
}

private suspend fun SequenceScope<LongRange>.afterLastIntersect(prev: LongRange, last: MapEntry) {
    val rangeEnd = prev.last
    if (rangeEnd > last.sourceEndExclusive) {
        yield(last.sourceEndExclusive..rangeEnd)
    }
}

private fun MapEntry.mapToDestination(prev: LongRange): LongRange =
    mapToDestination(max(prev.first, sourceStart))..mapToDestination(min(prev.last, sourceEnd))

infix fun LongRange.intersects(other: LongRange): Boolean {
    return max(first, other.first) < min(last, other.last)
}
