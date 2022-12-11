fun main() {
    fun List<String>.checkIfFullyOverlap(): Boolean {
        val firstPair = get(0).split('-').run {
            get(0).toInt() to get(1).toInt()
        }
        val secondPair = get(1).split('-').run {
            get(0).toInt() to get(1).toInt()
        }
        return firstPair.first <= secondPair.first && firstPair.second >= secondPair.second ||
                secondPair.first <= firstPair.first && secondPair.second >= firstPair.second
    }

    fun List<String>.checkIfFullyNotOverlap(): Boolean {
        val firstPair = get(0).split('-').run {
            get(0).toInt() to get(1).toInt()
        }
        val secondPair = get(1).split('-').run {
            get(0).toInt() to get(1).toInt()
        }
        return firstPair.second < secondPair.first || firstPair.first > secondPair.second
    }

    // fully overlapping
    fun part1(input: List<String>): Int =
        input.filter {
            it.split(',').checkIfFullyOverlap()
        }.size

    // partially overlapping
    fun part2(input: List<String>): Int =
        input.filterNot {
            it.split(',').checkIfFullyNotOverlap()
        }.size

    val testInput = readInput("04", true)
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("04")
    part1(input).println()
    part2(input).println()
}


