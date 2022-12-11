/**
 * https://adventofcode.com/2022/day/1
 */
fun main() {
    fun List<String>.toGroupList(): ArrayList<List<String>> {
        val inputs = ArrayList<List<String>>()
        var curList = arrayListOf<String>()
        forEach {
            if (it.isNotEmpty()) {
                curList += it
            } else {
                inputs.add(curList.toList())
                curList = arrayListOf()
            }
        }
        inputs.add(curList)
        return inputs
    }

    fun part1(input: List<String>): Int {
        val inputs = input.toGroupList()
        return inputs.maxOfOrNull { it -> it.sumOf { it.toInt() } } ?: 0
    }

    fun part2(input: List<String>): Int {
        val inputs = input.toGroupList()
        val sortedList = inputs.map { it -> it.sumOf { it.toInt() } }.sortedDescending()
        return sortedList.run {
            get(0) + get(1) + get(2)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("01", true)
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("01")
    part1(input).println()
    part2(input).println()
}
