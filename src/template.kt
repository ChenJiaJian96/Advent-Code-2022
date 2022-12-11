fun main() {
    fun part1(input: List<String>): Int = 0

    fun part2(input: List<String>): Int = 0

    val testInput = readInput("01", true)
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

    val input = readInput("01")
    part1(input).println()
    part2(input).println()
}
