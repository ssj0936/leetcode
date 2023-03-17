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
class Solution:Sol {
    var maxProfit = 0;
    override fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {

    }

    private fun foo(startTime: IntArray, endTime: IntArray, index: Int, currentProfit:Int):Int{
        //先找起點
        val start = startTime[index]
        var tmp = index
        var count = 1
        while (tmp+1<startTime.lastIndex && startTime[tmp+1] == start)
            ++count

        for(i in 0 ..count){
            var profit = foo(startTime, endTime, endTime[index + i])
        }



    }

    private fun binarySearch(target:Int, source:IntArray, start:Int = 0, end:Int = source.lastIndex):Int{
        var head = start
        var tail = end

        while (head<tail){
            val midIndex = (head + end)/2
            if(target == source[midIndex]) break
            else if(target<source[midIndex])
                tail = midIndex-1
            else
                head = midIndex+1
        }

        return -(head+1)
    }
}
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}