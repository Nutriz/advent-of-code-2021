import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

@OptIn(ExperimentalTime::class)
inline fun <T> measureAndLog(textToLog: String = "measured", block: () -> T): T {
    val timed = measureTimedValue(block)
    println("$textToLog ${timed.duration}")
    return timed.value
}

fun check(part: String, result: Int, excepted: Int) {
    val sign = if (result == excepted) "==" else "!="
    val emoji = if (result == excepted) "✅" else "⚠️"
    println("check $part: $result $sign $excepted $emoji")
}

fun check(part: String, result: Long, excepted: Long) {
    val sign = if (result == excepted) "==" else "!="
    val emoji = if (result == excepted) "✅" else "⚠️"
    println("check $part: $result $sign $excepted $emoji")
}

/**
 * Extension functions
 */
fun String.binToInt(): Int = Integer.parseInt(this,2)

fun String.overlaps(other: String): Boolean = this.toSet().containsAll(other.toSet())
