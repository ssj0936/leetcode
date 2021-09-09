/*
78. Subsets(Medium)
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */


/*
想法：for(i in 0 .. 2^nums.size)
把i轉成binary，0代表該位不要出現 1代表該位應該要出現

本來的寫法是用pow和餘數之類的去寫，但其實用bit shift會快一些
2^n 可以寫成： 1 shl n
n/2 可以寫成： n shr 1
n%2 可以用：n and 1 來檢查
會快很多
 */
class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val outputSize = 1 shl nums.size
        val output = mutableListOf<List<Int>>()
        for(i in 0 until outputSize){
            var tmp = i
            val list = mutableListOf<Int>()
            var index = 0
            while (tmp!=0) {
                if((tmp and 1) == 1) list.add(nums[index])
                tmp = tmp shr 1
                ++index
            }
//            println(list)
            output.add(list)
        }
        return output
    }
}

fun main(args: Array<String>) {
    println("${8 and 1}")
    val input = intArrayOf(1,2,3)
    val result = Solution().subsets(input)
    println(result)
}