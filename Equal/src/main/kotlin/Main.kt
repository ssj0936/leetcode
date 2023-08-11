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
 * Complete the 'equal' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

fun equal(arr: Array<Int>): Int {
    // Write your code here
    var minAll = Int.MAX_VALUE
    var min = arr.min()!!

    for(i in 0 .. 4){
        val target = min - i

        var count = 0
        for(el in arr){
            val dist = el - target
            count += (dist/5) + (dist%5/2) + dist%5%2
        }
        minAll = minOf(minAll, count)
    }
    return minAll
}

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()

    for (tItr in 1..t) {
        val n = readLine()!!.trim().toInt()

        val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

        val result = equal(arr)

        println(result)
    }
}
