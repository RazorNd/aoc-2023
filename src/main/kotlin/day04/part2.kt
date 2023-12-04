package day04

import java.io.FileInputStream


private data class CardCount(val card: ScratchCard, var count: Int = 1) {
    fun addCard(n: Int) {
        this.count += n
    }
}

fun main() {
    val cards = parseCards(FileInputStream("input/day04/input.txt")).map { CardCount(it) }.toList()

    cards.forEachIndexed { index, (card, count) ->
        val matchingNumber = card.matchingNumber

        cards.subList(index + 1, index + matchingNumber + 1).forEach { it.addCard(count) }
    }

    println(cards.sumOf { it.count })
}