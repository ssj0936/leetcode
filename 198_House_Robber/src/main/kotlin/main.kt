/*
198. House Robber(Medium)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400

 */
class Solution {

    /*
    比較直觀的解
    dp[i] 代表偷到第i間房子(包含第i間)的最大金額
    礙於現制，不能偷相鄰的房子，所以dp[i]有可能是 dp[i-2] + 自己，或是dp[i-3] + 自己
    那為什麼不用dp[i-4]？因為如果dp[i-2]一定會包含dp[i-4]

    最後再從所有的答案中挑出最大的
     */

    fun rob(nums: IntArray): Int {
        if(nums.size==1) return nums[0]
        else if(nums.size==2) return Math.max(nums[0], nums[1])

        val dp = Array(nums.size){0}
        dp[0] = nums[0]
        dp[1] = nums[1]

        for(i in 2 until nums.size){
            dp[i] = Math.max(
                if(i-2<0) 0 else dp[i-2],
                if(i-3<0) 0 else dp[i-3]) + nums[i]
        }
        return dp.max()?:0
    }

    /*
    更改dp[i]的定義
    dp[i] = 偷盜第i間為止最大金額
    dp[i]就可能是 偷了第i間：dp[i-2]+自己 或是不偷第i間：dp[i-1]
    最後回傳dp[n-1]

     */

    fun robV2(nums: IntArray): Int {
        if(nums.size==1) return nums[0]
        else if(nums.size==2) return Math.max(nums[0], nums[1])

        val dp = Array(nums.size){0}
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])

        for(i in 2 until nums.size){
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1])
        }
        return dp[nums.size-1]
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(2,7,9,3,1)
    val result = Solution().robV2(input)
    println(result)
}