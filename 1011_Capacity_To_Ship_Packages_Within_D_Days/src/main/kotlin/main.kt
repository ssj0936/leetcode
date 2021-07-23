/*
1011. Capacity To Ship Packages Within D Days(Medium)
A conveyor belt has packages that must be shipped from one port to another within days days.
The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500

 */

class Solution {
    fun shipWithinDaysOrigin(weights: IntArray, days: Int): Int {
        val dp = IntArray(weights.size).apply {
            this[0] = weights[0]
            for(i in 1 until this.size){
                this[i] = this[i-1] + weights[i]
            }
        }
        println(weights.contentToString())
        println(dp.contentToString())


        var capacity: Int
        var min = Int.MAX_VALUE
        for(i in dp.indices){
            capacity = dp[i]
            var count = 1
            var pivot = i

            if(capacity>min) break

            var finish = false
            for(j in i+1 until dp.size){
                if(count>days) break

                var currentAccumulator = dp[j]-dp[pivot]
                println("capacity:$capacity, pivot:$pivot, j:$j,  currentAccumulator:$currentAccumulator")

                //掃到下一格 就爆了，乾脆直接下一輪
//                if(currentAccumulator > capacity && j-1 == pivot){
//                    break
//                }
                //掃到最後一格
                /*else */if(j == dp.size-1){
                    //目前累計比capacity更大 -> 把目前capacity更新為累計值
                    if(currentAccumulator > capacity) capacity = currentAccumulator
                    ++count
                    finish = true
                }
                else if(currentAccumulator >= capacity){
                    capacity = currentAccumulator
                    ++count
                    pivot = j
                    println("capacity更新:$capacity")
                }
            }

            if(finish && count<=days && capacity < min){
                println("count:$count, min更新:$capacity")
                min = capacity
            }
            println("---------------------------------")
        }

        return min
    }

    fun shipWithinDays(weights: IntArray, days: Int): Int {
        val min = weights.maxOrNull()?:0
        val max = weights.sum()

        var i = min
        var j = max
        while (i<j){
            var mid = (i+j)/2

            val day = daysNeed(weights,mid)
            println("capa:$mid ---> day:$day")

            if(day>days) i = mid+1
            else if(day == days) j = mid
            else j = mid
        }
        return i
    }

        fun daysNeed(weights: IntArray, capacity:Int):Int{
            var days = 1
            var accumulator = 0
            for(i in weights){
                if((accumulator + i)>capacity) {
                    ++days
                    accumulator = 0
                }
                accumulator +=i
        }

        return days
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    val day = 5
    val result = Solution().shipWithinDays(input,day)
    println(result)
}