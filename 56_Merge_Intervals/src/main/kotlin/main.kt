/*
56. Merge Intervals(Medium)
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:
1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4

 */
class Solution {
    /*
    之前做過skyline的題目，覺得蠻類似的，但一樣有很多細節

    想法是把interval們的起點和終點個用一個hashmap裝起來(hashmap<index,數量>)
    本來只有用set確認這個index是不是起終點而已，直到遇到([1,4][1,5])這種狀況

    主要概念就是遇到一個interval的起點，就把counter+1，counter從0跳起來表示一個interval的開始
    遇到一個interval的終點，就把counter-1，而counter降為0就代表一個interval的結束

    然後從最小loop到最大的index 如果目前index在起點map裡面，就把counter 加上 map裡面該index的起點數量(並且把map中的自己刪掉)
    如果目前index在終點map裡面，就把counter 減去上 map裡面該index的終點數量(並且把map中的自己刪掉)

    另外這邊多用兩個flag，主要是遇到([0,0][1,4])這種，不屬於任何一個interval但自成一個interval的起終同一點型

     */

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val mapStart:HashMap<Int,Int> = HashMap()
        val mapEnd:HashMap<Int,Int> = HashMap()

        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        for(i in intervals){
            mapStart[i[0]] = mapStart.getOrDefault(i[0],0)+1
            mapEnd[i[1]] = mapEnd.getOrDefault(i[1],0)+1

            if(i[0]<min) min = i[0]
            if(i[1]>max) max = i[1]
        }

        var result = arrayListOf<IntArray>()
        var count = 0
        var interval = intArrayOf(-1,-1)
        for(i in min .. max){
            var originCount = count
            var startFlag = false
            var endFlag = false
            if(mapStart[i]!=null && mapStart[i]!!>0){
                count+=mapStart.getOrDefault(i,0)
                mapStart[i] = mapStart[i]!!-1

                if(originCount==0 && count>0){
                    startFlag = true
                    originCount = count
                }
            }

            if(mapEnd[i]!=null && mapEnd[i]!!>0){
                count-=mapEnd.getOrDefault(i,0)
                mapEnd[i] = mapEnd[i]!!-1

                if(originCount>0 && count ==0){
                    endFlag = true
                }
            }

            if(startFlag)
                interval[0] = i
            if(endFlag){
                interval[1] = i
                result.add(interval.clone())
            }
        }
        return result.toTypedArray()
    }

    /*
    但其實後來發現，有些問題其實直觀的解就可以了
    依照慣例先sort(依照起點)，然後抓起第一個interval當基準，往後一個interval檢查 有沒有跟自己重疊到
    重疊的定義為：我的終點 比 後者起點 大
    如果重疊 就把目前的interval終點更新成後者，如果沒有重疊 那就代表這個interval完整斷開了，就抓下一個起來當作基準
     */
    fun mergeV2(intervals: Array<IntArray>): Array<IntArray> {
        //對起點sort
        intervals.sortBy { it[0] }

        var currInterval = intervals[0]
        val result = mutableListOf<IntArray>()
        for(interval in intervals){
            //如果重疊
            if(currInterval[1] >= interval[0])
                currInterval[1] = interval[1]
            else {
                result.add(currInterval.clone())
                currInterval = interval
            }
        }
        //最後一個收尾
        result.add(currInterval.clone())

        return result.toTypedArray()
    }
}

fun main(args: Array<String>) {
    val input = arrayOf(
//        intArrayOf(1,3),
//        intArrayOf(2,6),
//        intArrayOf(8,10),
//        intArrayOf(15,18)

//        intArrayOf(1,4),
//        intArrayOf(4,5)

//        intArrayOf(1,4),
//        intArrayOf(1,5)

        intArrayOf(1,4),
        intArrayOf(0,0)
    )
    val result = Solution().mergeV2(input)
    for(i in result){
        println(i.contentToString())
    }
}