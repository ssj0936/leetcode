/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
 */

interface Sol{
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int
}
class Solution{
    private val DEBUG = false
    private val hashtable = HashMap<Int, Int>()

    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val array = Array<Node?>(startTime.size){null}
        for(i in startTime.indices){
            array[i] = Node(startTime[i], endTime[i], profit[i])
        }
        array.sortBy { it!!.startTime }
        for(i in startTime.indices){
            startTime[i] = array[i]!!.startTime
            endTime[i] = array[i]!!.endTime
            profit[i] = array[i]!!.profit
        }

        //debugPrint("startTime:${startTime.contentToString()}")
        //debugPrint("endTime:${endTime.contentToString()}")
        //debugPrint("profit:${profit.contentToString()}")


        for(i in startTime.indices){
            val periodStart = startTime[i]
            val periodEnd = endTime[i]
            val periodProfit = profit[i]
            var periodEndIndex = startTime.binarySearch(periodEnd, i)
            if(periodEndIndex<0){
                periodEndIndex = (periodEndIndex+1)*-1
            }

            for(j in i .. Math.min(periodEndIndex, startTime.lastIndex)){
                if(periodStart<startTime[j] && periodEnd>endTime[j] && periodProfit<=profit[j]) {
                    startTime[i] = 0
                    endTime[i] = 0
                    profit[i] = 0
                }
            }
        }
        val newStartTime = startTime.filter { it != 0 }
        val newEndTime = endTime.filter { it != 0 }
        val newProfit = profit.filter { it != 0 }

        //debugPrint("newStartTime:${newStartTime}")
        //debugPrint("newEndTime:${newEndTime}")
        //debugPrint("newProfit:${newProfit}")


        val max = getMaxProfitFromIndex(newStartTime, newEndTime, newProfit, 0)
        return max
    }

    private fun getMaxProfitFromIndex(startTime: List<Int>, endTime: List<Int>, profit: List<Int>, index: Int):Int{
        if(index>startTime.lastIndex)
            return 0
        //先找起點
//        val start = startTime[index]
//        var tmp = index
//        var count = 1
//        while (tmp+1<startTime.lastIndex && startTime[tmp+1] == start) {
//            ++tmp
//            ++count
//        }
//        println("count:$count")
        var maxProfitWithThis = 0
//        for(i in 0 ..count){
        //debugPrint("index:$index, endTime[$index]:${endTime[index]}")

        var nextPeriodStartIndex = binarySearch(endTime[index], startTime, index+1)
        if(nextPeriodStartIndex<0){
            nextPeriodStartIndex = (nextPeriodStartIndex+1)*-1
        }
//            val p = startTime.binarySearch(endTime[index], index+1)

        //debugPrint("nextPeriodStartIndex:$nextPeriodStartIndex")
        val maxProfitFromThatIndex = profit[index] + hashtable.getOrElse(nextPeriodStartIndex){
            //debugPrint("@@")
            getMaxProfitFromIndex(startTime, endTime, profit, nextPeriodStartIndex)
        }
        maxProfitWithThis = Math.max(maxProfitWithThis, maxProfitFromThatIndex)
        //debugPrint("maxProfitWithThis($index), $maxProfitWithThis")

//        }
        val profitWithoutThis = getMaxProfitFromIndex(startTime, endTime, profit, index+1)
        //debugPrint("profitWithoutThis($index), $profitWithoutThis")

        val finalProfit = Math.max(maxProfitWithThis, profitWithoutThis)
        hashtable.put(index, finalProfit)
        return finalProfit
    }

    private fun binarySearch(target:Int, source:List<Int>, start:Int = 0, end:Int = source.lastIndex):Int{
//        debugPrint("target:$target, start:$start, end:$end")
        var head = start
        var tail = end

        while (head<=tail){
//            debugPrint("target:$target, head:$head, tail:$tail")
            var midIndex = (head + tail)/2
            if(target == source[midIndex]){
                while (midIndex-1>=0 && source[midIndex-1]==target) {
//                    debugPrint("pp")
                    --midIndex
                }
//                debugPrint("qq")
                return midIndex
            }
            else if(target<source[midIndex])
                tail = midIndex-1
            else
                head = midIndex+1
        }
//        debugPrint("-head-1:${-head-1}")
        return -head-1
    }

    data class Node(
        val startTime: Int,
        val endTime:Int,
        val profit: Int
    )

    fun debugPrint(string: String){
        if(DEBUG)
            println(string)
    }
}

//class SolutionDP {
//
//    private val hashtable = HashMap<Int, Int>()
//
//    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
//        val dp = Array<Array<Int>>(startTime.size){Array(startTime.size){0} }
//
//        for(i in startTime.indices){
//            for()
//        }
//
//        return
//    }
//}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}