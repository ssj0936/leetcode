import kotlin.math.max

/*
53. Maximum Subarray(Easy)
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23

Constraints:

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 */

//DP經典題，重要的地方在於 要清楚定義DP[i]的意義
class Solution {
    fun maxSubArray(nums: IntArray): Int {
        //之前submit的，我是睡夢中解的嗎？
//        var max = nums[0]
//        var maxSubValueWithCurrentElement = nums[0]
//        for(i in nums.indices){
//            if(i == 0) continue
//
//            maxSubValueWithCurrentElement += nums[i]
//            if(maxSubValueWithCurrentElement< nums[i])
//                maxSubValueWithCurrentElement = nums[i]
//            if(maxSubValueWithCurrentElement>max)
//                max = maxSubValueWithCurrentElement
//        }
//        return max

        //recordArray[i] = 字尾為nums[i]的max subsequence sum
        //recordArray[0] 理所當然為 nums[0]
        //recordArray[1] 則可能為：
        // 1.字尾為nums[0]的max subsequence sum + 自己
        // 2.自己
        //所以選max填入

        //最後找出recordArray中最大的值回傳
        val recordArray =Array<Int>(nums.size){0}.apply {
            this[0] = nums[0]
        }
        var max = nums[0]
        for(i in 1 until nums.size){
            recordArray[i] = Math.max(recordArray[i-1]+nums[i],nums[i])
            if(recordArray[i]>max) max = recordArray[i]
        }
        return max
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)
    val result = Solution().maxSubArray(input)
    println(result)
}