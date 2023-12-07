package day07

import java.io.FileInputStream

private fun String.toHandType(): HandType = toHandType { eachCount ->
    val values = eachCount.filterKeys { it != 'J' }.values.sortedDescending()

    listOf((values.firstOrNull() ?: 0) + (eachCount['J'] ?: 0)) + values.drop(1)
}

private fun Char.toCardPower() = when (this) {
    in '2'..'9' -> toString().toInt()
    'T' -> 10
    'J' -> 1
    'Q' -> 12
    'K' -> 13
    'A' -> 14
    else -> throw IllegalArgumentException("Incorrect card '${this}'")
}

fun main() {
    val total = FileInputStream("input/day07/input.txt")
        .calcTotalWining(compareBy<String> { it.toHandType() }.then(compareEachCard { it.toCardPower() }))

    println(total)
}
