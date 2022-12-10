fun main() {
    fun findSameChar(first: String, second: String): Char {
        val firstSet = first.toList().toSet()
        second.forEach {
            if (firstSet.contains(it)) return it
        }
        return 'a'
    }

    fun findSameChar(first: String, second: String, third: String): Char {
        val firstSet = first.toList().toSet()
        val firstAndSecond = mutableSetOf<Char>()
        second.forEach {
            if (firstSet.contains(it)) firstAndSecond.add(it)
        }
        third.forEach {
            if (firstAndSecond.contains(it)) return it
        }
        return 'a'
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val size = it.length
            val first = it.substring(0, size / 2)
            val second = it.substring(size / 2)
            findSameChar(first, second).toVal()
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3) {
            findSameChar(it[0], it[1], it[2]).toVal()
        }.sum()
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

fun Char.toVal() =
    if (this.isLowerCase()) {
        this.toInt() - 96
    } else {
        this.toInt() - 38
    }