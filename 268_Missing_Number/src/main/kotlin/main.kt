/*
268. Missing Number(Easy)

Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?

Example 1:
Input: nums = [3,0,1]
Output: 2

Example 2:
Input: nums = [0,1]
Output: 2

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8

Example 4:
Input: nums = [0]
Output: 1

Constraints:

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
 */

class Solution {
    fun missingNumber(nums: IntArray): Int {
        var completeSum = (nums.size + 0)*(nums.size+1)/2
        for(num in nums)
            completeSum -= num
        return completeSum
//
//        val array = Array(nums.size+1){-1}
//        for(num in nums){
//            array[num] = 0
//        }
//
//        return array.indexOf(-1)
    }
}

fun main(args: Array<String>) {
    val array = intArrayOf(9,6,4,2,3,5,7,0,1)
    val result = Solution().missingNumber(array)
    println(result)
}