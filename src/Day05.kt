fun main() {

    fun MutableList<MutableList<Char>>.addOriginChars(originData: String) {
        originData.forEachIndexed { index, c ->
            if (c == '[') {
                this[index / 4].add(originData[index + 1])
            }
        }
    }

    fun MutableList<MutableList<Char>>.execAction(moveActions: MoveAction, needReverse: Boolean) {
        val newList = this[moveActions.startIndex].subList(0, moveActions.quantity)
        if (needReverse) {
            newList.reverse()
        }
        this[moveActions.endIndex].addAll(0, newList)
        IntRange(0, moveActions.quantity - 1).reversed().forEach {
            this[moveActions.startIndex].removeAt(it)
        }
    }

    fun extracMoveActions(
        input: List<String>,
        moveActionStartIndex: Int
    ): List<MoveAction> {
        val moveActions = input.subList(moveActionStartIndex, input.size).map {
            it.split(" ").run {
                MoveAction(
                    quantity = get(1).toInt(),
                    startIndex = get(3).toInt() - 1, // change to index
                    endIndex = get(5).toInt() - 1, // change to index
                )
            }
        }
        return moveActions
    }

    fun extractOriginalData(input: List<String>): MutableList<MutableList<Char>> {
        val emptyLineIndex = input.indexOf("")

        val numLineIndex = emptyLineIndex - 1
        val totalSize = input[numLineIndex].length / 4 + 1

        val originalData: MutableList<MutableList<Char>> = MutableList(totalSize) { mutableListOf() }
        input.subList(0, numLineIndex).forEach {
            originalData.addOriginChars(it)
        }
        return originalData
    }

    fun part1(input: List<String>): String {
        val emptyLineIndex = input.indexOf("")
        val originalData = extractOriginalData(input)
        val moveActions = extracMoveActions(input, emptyLineIndex + 1)
        moveActions.forEach {
            originalData.execAction(it, true)
        }

        return originalData.map {
            it.first()
        }.joinToString("") { it.toString() }
    }

    fun part2(input: List<String>): String {
        val emptyLineIndex = input.indexOf("")
        val originalData = extractOriginalData(input)
        val moveActions = extracMoveActions(input, emptyLineIndex + 1)
        moveActions.forEach {
            originalData.execAction(it, false)
        }

        return originalData.map {
            it.first()
        }.joinToString("") { it.toString() }
    }

    val testInput = readInput("05", true)
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("05")
    part1(input).println()
    part2(input).println()
}

data class MoveAction(
    val quantity: Int,
    val startIndex: Int, // start from 0
    val endIndex: Int, // max is last_index
)