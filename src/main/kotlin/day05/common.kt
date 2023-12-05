package day05

import java.io.InputStream

internal data class MapEntry(val destinationStart: Long, val sourceStart: Long, val length: Long)

internal val MapEntry.sourceRange: LongRange get() = sourceStart..<(sourceStart + length)

internal fun parseMapEntry(line: String): MapEntry {
    val (destination, source, length) = line.split(" ").map { it.toLong() }

    return MapEntry(destination, source, length)
}

internal fun parseMaps(blocks: List<String>): List<List<MapEntry>> = blocks.map { block ->
    block.lineSequence().drop(1).map { parseMapEntry(it) }.toList()
}

internal fun parseBlocks(inputStream: InputStream) = inputStream
    .bufferedReader()
    .readText()
    .split("\n\n")

internal fun MapEntry.mapToDestination(source: Long): Long = source - sourceStart + destinationStart