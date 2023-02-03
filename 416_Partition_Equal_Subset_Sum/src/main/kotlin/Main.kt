/*
416. Partition Equal Subset Sum
Medium
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun canPartition(nums: IntArray): Boolean
}

class Solution:Sol {
    override fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if(sum%2 == 1) return false

        val target = sum/2
        val set = mutableSetOf(0)

        for(num in nums){
            val size = set.size
            for(i in 0 until size){
                val result = set.elementAt(i) + num
                if(result == target) return true

                set.add(result)
            }
        }
        return false
    }
}

class Solution2DDP:Sol {
    override fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if(sum%2 == 1) return false

        val target = sum/2
        //dp[i][j]表示：array裡面前i個element能不能組成j
        val dp = Array(nums.size+1){Array(target+1){false}}

        //dp[0][?] = false，前0位不能夠組成任何東西，所以是false
        for(i in 0 until (target+1) ){
            dp[0][i] = false
        }

        //dp[?][0] = true，一定能夠組成0，所以是true
        for(i in 0 until (nums.size+1) ){
            dp[i][0] = true
        }

        //dp[0][0] = true，前面0位能夠組成0，true
        dp[0][0] = true

        for (i in 1 .. nums.size){
            for (j in 1 .. target){
                //如果j沒有這一位大，那這一位一定不可能被算進來
                dp[i][j] = if(j >= nums[i-1]) (dp[i-1][j] || dp[i-1][j-nums[i-1]]) else dp[i-1][j]
            }
        }

        return dp[nums.size][target]
    }
}

class Solution1DDP:Sol {
    override fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if(sum%2 == 1) return false

        val target = sum/2
        //dp[j]表示：array裡面element能不能組成j
        val dp = Array(target+1){false}

        //dp[0][0] = true，前面0位能夠組成0，true
        dp[0] = true

        for (num in nums){
            for (j in 1 .. target){
                if(j<num) continue
                if(dp[j-num])
                    dp[j] = dp[j-num]
            }
        }

        return dp[target]
    }
}