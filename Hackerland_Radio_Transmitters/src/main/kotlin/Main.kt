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
 * Complete the 'hackerlandRadioTransmitters' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY x
 *  2. INTEGER k
 */

fun hackerlandRadioTransmitters(x: Array<Int>, k: Int): Int {
    //sort first for binarySearch
    x.sort()
    return helper(x,k,0)
}

private fun helper(x: Array<Int>, k: Int, index:Int):Int{
    if(index>x.lastIndex)
        return 0

    val firstTransmitterPosUpperBound = x[index] + k
    val settleHouseIndex = run{
        val tmp = x.bs(firstTransmitterPosUpperBound, index)
        //found, then use it,
        //not found, then index -1
        if(tmp>=0) tmp else -(tmp+1)-1
    }

    val firstTransmitterCoverUpperBound = x[settleHouseIndex] + k
    val nextRoundStartIndex = run{
        val tmp = x.bs(firstTransmitterCoverUpperBound, settleHouseIndex)
        //found, means overlapped need to +1
        //not found, mean it's not overlapped
        if(tmp>=0) tmp+1 else -(tmp+1)
    }

    return helper(x, k, nextRoundStartIndex) +1
}

//extend function for customize binarySearch because of Constrain said:
//"There may be more than one house at the same location."
//so if group of target are found, it needs to return rightest one
private fun Array<Int>.bs(el:Int, start:Int):Int{
    var front = start
    var end = this.lastIndex

    while(front<=end){
        val mid = front + (end - front)/2
        if(el >= this[mid]){
            front = mid+1
        }else{
            end = mid -1
        }
    }

    return if(front<this.size && this[front]==el) front else -(front+1)
}

fun main(args: Array<String>) {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n = first_multiple_input[0].toInt()

    val k = first_multiple_input[1].toInt()

    val x = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = hackerlandRadioTransmitters(x, k)

    println(result)
}
