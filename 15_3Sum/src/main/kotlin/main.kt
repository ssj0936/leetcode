/*
15. 3Sum(Medium)

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:
Input: nums = []
Output: []

Example 3:
Input: nums = [0]
Output: []

Constraints:
0 <= nums.length <= 3000
-105 <= nums[i] <= 105

 */

class Solution {

    fun threeSumBrute(nums: IntArray): List<List<Int>> {

        if(nums.size<3) return listOf()

        val result = mutableSetOf<List<Int>>()
        for(i in 0 .. nums.size-3) {
            for(j in i+1 .. nums.size-2) {
                for(k in j+1 .. nums.size-1) {
                    if(nums[i]+nums[j]+nums[k] == 0)
                        result.add(listOf(nums[i],nums[j],nums[k]).sorted())
                }
            }
        }
        return result.toList()
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        if(nums.size<3) return listOf()

        nums.sort()
        val result = mutableListOf<List<Int>>()
        for(i in 0 until nums.size-2){
            if(i!=0 && nums[i]==nums[i-1]) continue

            val target = -nums[i]
            var j = i+1
            var k = nums.size-1

            while (j<k){
                if(nums[j]+nums[k] == target) {
                    result.add(listOf(nums[i], nums[j], nums[k]))

                    //加速重點 同時也是去除重複的關鍵
                    while (j+1<k && nums[j]==nums[j+1]){
                        ++j
                    }
                    ++j

                    //加速重點 同時也是去除重複的關鍵
                    while (k-1>j && nums[k]==nums[k-1]){
                        --k
                    }
                    --k

                }else if(nums[j]+nums[k] < target)
                    ++j
                else
                    --k
            }
        }
        return result
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(-1,0,1,2,-1,-4)
    val result = Solution().threeSum(input)
    println(result)
}