package day04

import java.io.InputStream
import java.util.regex.Pattern

internal data class ScratchCard(val wining: Set<Int>, val actual: Set<Int>) {
    val matchingNumber: Int get() = wining.intersect(actual).size

}

internal fun parseCards(inputStream: InputStream) = inputStream
    .bufferedReader()
    .lineSequence()
    .map { line ->
        val (_, cardNumbers) = line.split(Pattern.compile(":\\s+"), limit = 2)


        val (wining, actualNumber) = cardNumbers.split(Pattern.compile(" \\|\\s+"), limit = 2)
            .map { numbers -> numbers.split(Pattern.compile("\\s+")).map { it.trim().toInt() }.toSet() }

        ScratchCard(wining, actualNumber)
    }