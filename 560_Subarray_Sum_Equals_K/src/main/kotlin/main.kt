/*
560. Subarray Sum Equals K(Medium)
Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:

1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7

HINT:
Will Brute force work here? Try to optimize it.
Can we optimize it by using some extra space?
What about storing sum frequencies in a hash table? Will it be useful?
sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1. Can we use this property to optimize it.
 */

class Solution {
    /*
    會超出時間的解法
     */
    fun subarraySum(nums: IntArray, k: Int): Int {
        val hashMap = HashMap<Int,Int>()
        var sum = 0
        hashMap[0] = sum
        for((i,v) in nums.withIndex()){
            sum += v
            hashMap[i+1] = sum
        }

        var subArrayCount = 0
        val n = hashMap.size
        for(i in 0 until n){
            val sumStart:Int = hashMap.getValue(i)
            val target = sumStart+k
            println("sumStart:$sumStart")
            println("target:$target")
            hashMap.remove(i)
            if(hashMap.containsValue(target)){
                subArrayCount += hashMap.filterValues { it == target }.count()
            }
            println("-------------------------------")

        }

        return subArrayCount
    }
    /*
    hashmap sum(i,j)紀錄出現的次數
    這是先算再走的解法，所以需要再計算之前 把自己一次先扣掉
    時間複雜度：
    O(n+n) = O(n)
     */
    fun subarraySumV2(nums: IntArray, k: Int): Int {
        //紀錄sum值和頻率
        val hashMap = HashMap<Int,Int>()
        //紀錄sum(0,j)
        var array = IntArray(nums.size).apply {
            this[0] = 0
        }

        var sum = 0
        for((i,v) in nums.withIndex()){
            sum += v
            array[i] = sum
            hashMap[sum] = (hashMap[sum]?:0) + 1
        }

        var subArrayCount = array.count { it == k }
//        val n = hashMap.size
        for(i in array.indices){
            val sumStart:Int = array[i]
            val target = sumStart+k
            if(hashMap[sumStart]!=null){
                hashMap[sumStart] = hashMap[sumStart]!!-1
            }
            if(hashMap[target]!=null){
                subArrayCount += hashMap[target]!!
            }
        }

        return subArrayCount
    }

    /*
    邊走邊算型
     */
    fun subarraySumV3(nums: IntArray, k: Int): Int {
        /*
        紀錄sum值和頻率，有一個小地方要注意，這邊會先push 0
        否則遇到需要從頭累進到目前index的狀況，會無法囊括近來(因為沒有0 所以他只會從第一個開找)
         */
        val hashMap = HashMap<Int,Int>().apply { this[0]=1 }

        var sum = 0
        var subArrayCount = 0
        for(i in nums.indices){
            sum += nums[i]
            var start = sum - k
            if(hashMap.containsKey(start)) subArrayCount+=hashMap.getValue(start)
            hashMap[sum] = hashMap.getOrDefault(sum,0)+1
        }
        return subArrayCount
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(1,2,3,5,7,8)

//    val input = intArrayOf(-1,-1,1)
    val k = 8
    val result = Solution().subarraySumV3(input,k)
    println(result)
}