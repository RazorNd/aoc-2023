package day07

import java.io.InputStream

internal enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND,
}

internal fun compareEachCard(cardPowerFunction: (Char) -> Int): Comparator<String> = Comparator { o1, o2 ->
    o1.toCardPowers(cardPowerFunction).zip(o2.toCardPowers(cardPowerFunction))
        .firstOrNull { (a, b) -> a != b }
        ?.let { (a, b) -> a - b } ?: 0
}

internal fun InputStream.calcTotalWining(handComparator: Comparator<String>): Int = parseCardsWithBids(this)
    .sortedWith(compareBy(handComparator) { it.first })
    .mapIndexed { index, handWithBid -> (index + 1) * handWithBid.second }
    .sum()

internal fun String.toHandType(typeSelector: (Map<Char, Int>) -> Collection<Int>) =
    typeSelector(groupingBy { it }.eachCount())
        .sortedDescending()
        .toHandType()

private fun List<Int>.toHandType() = when {
    this[0] == 5 -> HandType.FIVE_OF_A_KIND
    this[0] == 4 -> HandType.FOUR_OF_A_KIND
    this[0] == 3 && this[1] == 2 -> HandType.FULL_HOUSE
    this[0] == 3 -> HandType.THREE_OF_A_KIND
    this[0] == 2 && this[1] == 2 -> HandType.TWO_PAIR
    this[0] == 2 -> HandType.ONE_PAIR
    else -> HandType.HIGH_CARD
}

private fun String.toHandWithBid(): Pair<String, Int> = split(" ", limit = 2).let { (hand, bid) -> hand to bid.toInt() }

private fun parseCardsWithBids(inputStream: InputStream) = inputStream
    .bufferedReader()
    .readLines()
    .map { it.toHandWithBid() }

private fun String.toCardPowers(cardPowerFunction: (Char) -> Int): Collection<Int> = map { cardPowerFunction(it) }
