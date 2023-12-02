package day02

internal enum class Color {
    RED, GREEN, BLUE
}

internal data class Cubes(val count: Int, val color: Color)
internal data class Game(val gameNumber: Int, val sets: List<List<Cubes>>)

internal fun String.parseGame(): Game {
    val (gameString, gameSets) = split(": ", limit = 2)

    val number = gameString.substring("Game ".length).toInt()

    val sets = gameSets.split("; ").map { it.parseSet() }

    return Game(number, sets)
}

private fun String.parseSet() = split(", ").map { cube ->
    val (count, color) = cube.split(" ", limit = 2)

    Cubes(count.toInt(), color.toColor())
}

private fun String.toColor() = Color.valueOf(uppercase())