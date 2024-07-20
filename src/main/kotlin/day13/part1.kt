package day13

import java.io.FileInputStream


typealias CharArray2 = Array<CharArray>
fun List<String>.toCharArray2() = Array(size) { get(it).toCharArray() }

fun CharArray2.size2(): Pair<Int, Int> {
    val n = size
    val m = get(0).size
    for (i in 1..<n) require(get(i).size == m) { "Row $i has size ${get(i)}, but expected $m" }
    return n to m
}

internal class Pattern(input: String) {
    private val pattern = input.lines()

    private val columnsCount = pattern.first().length

    fun findVerticalRefraction(): Int {
        for (k in 1..<(columnsCount - 1)) {
            var ok = true
            for (i in pattern.indices) for (j in 0..<minOf(columnsCount - k, k)) {
                if (pattern[i][k - j - 1] != pattern[i][j + k]) ok = false
            }
            if (ok) return k
        }
        return 0
    }

    fun findHorizontalRefraction(): Int {
        val indices = pattern.first().indices
        for (k in 1..<(pattern.size)) {
            var ok = true
            for (i in 0..<minOf(pattern.size - k, k)) for (j in 0..<columnsCount) {
                if (pattern[k - i - 1][j] != pattern[i + k][j]) ok = false
            }
            if (ok) return k
        }
        return 0
    }


}

fun main() {

    val sum = FileInputStream("input/day13/input.txt")
        .bufferedReader()
        .readText()
        .split("\n\n")
//        .sumOf { aa ->
//            val a = aa.lines().toCharArray2()
//
//            var sum = 0L
//
//            val (m, n) = a.size2()
//            for (k in 1..<n) {
//                var ok = true
//                for (i in 0..<m) for (j in 0..<minOf(n - k, k)) {
//                    if (a[i][k - j - 1] != a[i][j + k]) ok = false
//                }
//                if (ok) sum += k
//            }
//            for (k in 1..<m) {
//                var ok = true
//                for (i in 0..<minOf(m - k, k)) for (j in 0..<n) {
//                    if (a[k - i - 1][j] != a[i + k][j]) ok = false
//                }
//                if (ok) sum += 100 * k
//            }
//
//            sum
//        }
        .map { Pattern(it) }
        .sumOf { it.findVerticalRefraction().toLong() + (it.findHorizontalRefraction().toLong() * 100) }

    println(sum)

}