package day12

internal fun arrangements(records: String, groups: List<Int>): Long = Arrangements(records, groups).value
private class Arrangements(private val records: String, private val groups: List<Int>) {
    private val dp = Array(records.length) {
        Array(groups.size + 1) { -1L }
    }

    val value = calc()

    private fun calc(position: Int = 0, currentGroup: Int = 0): Long {
        if (position > records.lastIndex) return if (currentGroup > groups.lastIndex) 1 else 0
        if (dp[position][currentGroup] >= 0) return dp[position][currentGroup]
        val res = when (records[position]) {
            '#' -> emplaceGroup(position, currentGroup)
            '.' -> calc(position + 1, currentGroup)
            '?' -> emplaceGroup(position, currentGroup) + calc(position + 1, currentGroup)

            else -> throw IllegalArgumentException("Illegal character")
        }
        dp[position][currentGroup] = res
        return res
    }

    private fun emplaceGroup(position: Int, group: Int): Long {
        val end = position + (groups.getOrNull(group) ?: return 0)

        if (end > records.length ||
            records.substring(position, end).any { it == '.' } ||
            records.getOrNull(end) == '#') return 0

        return calc(end + 1, group + 1)
    }
}