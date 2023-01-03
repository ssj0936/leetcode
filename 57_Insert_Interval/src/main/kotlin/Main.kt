import java.util.*

/**
 * 57. Insert Interval
Medium
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:
0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^5
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 10^5
 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray>
}

/*
分三部分
1, newInterval範圍之前的，push 進新的result
2, newInterval範圍之後的，push 進新的result
3. 剩下的，再分兩種可能，有重疊和沒重疊：
    沒重疊的話 這個newInterval會剛好塞進縫隙裡，只需要放在第一部分的個數(n1)的位置
    有重疊的話，跟重疊的這幾個interval去做最大上下限處理，整合成一個大的interval，然後一樣塞在第一部分的個數(n1)的位置
 */
class Solution:Sol {
    override fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var start = newInterval[0]
        var end = newInterval[1]
        var insertIndex = 0

        for(interval in  intervals){
//            println(interval.contentToString())
            if(interval[1]<start){
                result.add(interval)
                ++insertIndex
//                println("${interval.contentToString()} push")
            }
            else if(interval[0]>end){
                result.add(interval)
//                println("${interval.contentToString()} push")
            }else if(interval[1]>=start || interval[0]<=end){
                start = Math.min(start,interval[0])
                end = Math.max(end,interval[1])
//                println("start:$start, end:$end")
            }

        }
        result.add(insertIndex, intArrayOf(start,end))

//        println(result.toTypedArray().contentDeepToString())
        return result.toTypedArray()
    }
}