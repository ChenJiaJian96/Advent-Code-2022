import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: String, isTest: Boolean = false): List<String> {
    val fileName = "Day$day" + if (isTest) "_test" else "" + ".txt"
    return File("src/files", fileName).readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * get each char in a string
 */
operator fun String.component1() = this[0]
operator fun String.component2() = this[1]
operator fun String.component3() = this[2]
