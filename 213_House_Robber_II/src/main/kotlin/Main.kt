
class Solution {
    val ROB_FIRST = 1
    val NOT_ROB_FIRST = 0
    fun rob(nums: IntArray): Int {
        if(nums.size==1) return nums[0]

        val n = nums.size
        val dp = Array(n){IntArray(2)}

        var result = 0
        for(i in nums.indices){
            dp[i][NOT_ROB_FIRST] = run{
                val notTake = if(i-1>=0) dp[i-1][NOT_ROB_FIRST] else 0
                val take = (if(i==0) 0 else nums[i]) + if(i-2>=0) dp[i-2][NOT_ROB_FIRST] else 0
                maxOf(notTake, take)
            }

            dp[i][ROB_FIRST] = run{
                val notTake = if(i-1>=0) dp[i-1][ROB_FIRST] else 0
                val take = nums[i] + if(i-2>=0) dp[i-2][ROB_FIRST] else 0

                if(i == nums.lastIndex){
                    result = maxOf(dp[i][NOT_ROB_FIRST], notTake)
                }
                maxOf(notTake, take)
            }
        }

        return result
    }
}

class Solution {

    fun rob(nums: IntArray): Int {
        if(nums.size==1) return nums[0]
        return maxOf(helper(nums, 0, nums.lastIndex-1), helper(nums, 1, nums.lastIndex))
    }

    private fun helper(nums: IntArray, from:Int, to:Int):Int {
        var maxTwoStepBefore = 0
        var maxOneStepBefore = 0
        for(i in from .. to){
            var tmp = maxOneStepBefore
            maxOneStepBefore = maxOf(maxTwoStepBefore + nums[i], maxOneStepBefore)
            maxTwoStepBefore = tmp
        }

        return maxOneStepBefore
    }
}