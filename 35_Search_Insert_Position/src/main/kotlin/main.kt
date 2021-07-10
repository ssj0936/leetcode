/*
35. Search Insert Position(Easy)

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:
Input: nums = [1,3,5,6], target = 7
Output: 4

Example 4:
Input: nums = [1,3,5,6], target = 0
Output: 0

Example 5:
Input: nums = [1], target = 0
Output: 0

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
 */

//其實就是binary search
//請背熟
//重點在於return哪一個pointer
class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size-1
        while (l<=r){
            val index = (l+r)/2
            val mid = nums[index]
            when {
                target == mid -> return index
                target < mid -> --r
                else -> ++l
            }
        }
        return l
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(1)
    val result = Solution().searchInsert(input,0)
    println(result)
}