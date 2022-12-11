fun main() {
    fun findSameChar(first: String, second: String): Set<Char> {
        return first.toCharArray().toSet() intersect second.toCharArray().toSet()
    }

    fun findSameChar(first: String, second: String, third: String): Set<Char> {
        return first.toCharArray().toSet() intersect second.toCharArray().toSet() intersect third.toCharArray().toSet()
    }

    fun part1(input: List<String>): Int {
        return input.map {
            it.substring(0, it.length / 2) to it.substring(it.length / 2)
        }.flatMap {
            findSameChar(it.first, it.second)
        }.sumOf {
            it.toVal()
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3) {
            val (first, second, third) = it
            findSameChar(first, second, third)
        }.sumOf { chars ->
            chars.sumOf { it.toVal() }
        }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    check(part1(input) == 7597)
    check(part2(input) == 2607)
}

fun Char.toVal() =
    if (this.isLowerCase()) {
        this - 'a' + 1
    } else {
        this - 'A' + 27
    }