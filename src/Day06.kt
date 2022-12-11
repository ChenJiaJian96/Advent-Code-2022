fun main() {

    fun String.findFirstMarkerIndex(markerLength: Int): Int {
        for (index in 0 until this.length - markerLength) {
            this.substring(index, index + markerLength).toSet().also {
                if (it.size == markerLength) {
                    return index + markerLength
                }
            }
        }
        return -1
    }

    fun part1(input: List<String>): List<Int> =
        input.map {
            it.findFirstMarkerIndex(4)
        }

    fun part2(input: List<String>): List<Int> =
        input.map {
            it.findFirstMarkerIndex(14)
        }

    val testInput = readInput("06", true)
    check(part1(testInput) == listOf(7, 5, 6, 10, 11))
    check(part2(testInput) == listOf(19, 23, 23, 29, 26))

    val input = readInput("06")
    part1(input).println()
    part2(input).println()
}
