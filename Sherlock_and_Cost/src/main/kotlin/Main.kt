import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

/*
 * Complete the 'cost' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY B as parameter.
 */
const val PEEK = 0
const val VALLEY = 1

fun cost(B: Array<Int>): Int {
    // Write your code here
    val dp = Array(B.size){IntArray(2)}.apply {
        this[0][PEEK] = 0
        this[0][VALLEY] = 0
    }

    for(i in 1 .. B.lastIndex){
        dp[i][PEEK] = maxOf(dp[i-1][VALLEY] + (B[i]-1), dp[i-1][PEEK])
        dp[i][VALLEY] = maxOf(dp[i-1][PEEK] + (B[i-1]-1), dp[i-1][VALLEY])
    }
    return maxOf(dp[B.lastIndex][PEEK],dp[B.lastIndex][VALLEY])
}

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()

    for (tItr in 1..t) {
        val n = readLine()!!.trim().toInt()

        val B = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

        val result = cost(B)

        println(result)
    }
}
