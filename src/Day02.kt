/**
 * https://adventofcode.com/2022/day/2
 */
fun main() {

    fun String.calResult(): Int {
        val otherChoice = this[0].toChoice()
        val myChoice = this[2].toChoice()
        return myChoice.run {
            this.toScore() + this.challengeTo(otherChoice).toScore()
        }
    }

    fun part1(input: List<String>): Int = input.sumOf {
        it.calResult()
    }

    fun String.calResult2(): Int {
        val otherChoice = this[0].toChoice()
        val result = Result.fromChar(this[2])
        val myChoice = otherChoice.getTargetChoice(result)
        return myChoice.run {
            this.toScore() + this.challengeTo(otherChoice).toScore()
        }
    }

    fun part2(input: List<String>): Int = input.sumOf {
        it.calResult2()
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

enum class Choice {
    ROCK,
    PAPER,
    SCISSORS;

    fun toScore(): Int = when (this) {
        ROCK -> 1
        PAPER -> 2
        SCISSORS -> 3
    }
}

enum class Result {
    WIN,
    DRAW,
    LOSE;

    fun toScore(): Int = when (this) {
        WIN -> 6
        DRAW -> 3
        LOSE -> 0
    }

    companion object {
        fun fromChar(input: Char): Result =
            when (input) {
                'X' -> LOSE
                'Y' -> DRAW
                'Z' -> WIN
                else -> throw IllegalArgumentException("invalid input")
            }
    }
}

fun Choice.challengeTo(other: Choice): Result =
    if (this == other) {
        Result.DRAW
    } else if (
        (this == Choice.ROCK && other == Choice.SCISSORS) ||
        (this == Choice.SCISSORS && other == Choice.PAPER) ||
        (this == Choice.PAPER && other == Choice.ROCK)
    ) {
        Result.WIN
    } else {
        Result.LOSE
    }

fun Choice.getTargetChoice(result: Result): Choice =
    when (result) {
        Result.WIN -> {
            when (this) {
                Choice.ROCK -> Choice.PAPER
                Choice.PAPER -> Choice.SCISSORS
                Choice.SCISSORS -> Choice.ROCK
            }
        }
        Result.DRAW -> this
        Result.LOSE -> {
            when (this) {
                Choice.ROCK -> Choice.SCISSORS
                Choice.PAPER -> Choice.ROCK
                Choice.SCISSORS -> Choice.PAPER
            }
        }
    }

fun Char.toChoice(): Choice {
    return if (this == 'A' || this == 'X') {
        Choice.ROCK
    } else if (this == 'B' || this == 'Y') {
        Choice.PAPER
    } else if (this == 'C' || this == 'Z') {
        Choice.SCISSORS
    } else {
        throw IllegalArgumentException("invalid input")
    }
}