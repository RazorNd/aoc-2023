package day05

import java.io.FileInputStream


fun main() {
    val blocks = parseBlocks(FileInputStream("input/day05/input.txt"))

    val seeds = blocks.first().substring("seeds: ".length).split(" ").map { it.toLong() }

    val location = parseMaps(blocks.drop(1)).fold(seeds) { source, map ->
        source.map {
            val mapEntry = map.find { entry -> it in entry.sourceRange } ?: MapEntry(0, 0, 0)
            mapEntry.mapToDestination(it)
        }
    }.min()

    println(location)
}
