
class Solution {
    fun validPartition(nums: IntArray): Boolean {
        val dp = BooleanArray(nums.size+1).apply { this[0]=true }

        for(i in 1 .. nums.lastIndex){
            val dpI = i+1
            if(nums[i] == nums[i-1] && dp[i-2+1])
                dp[dpI] = true
            else if(nums[i] == nums[i-1] && ((i-2>=0 && nums[i-2]==nums[i]) && (i-2 >=0 && dp[i-3+1])))
                dp[dpI] = true
            else if(nums[i] == nums[i-1]+1 && (i-2>=0 && nums[i-1]==nums[i-2]+1 && (i-2 >=0 && dp[i-3+1])))
                dp[dpI] = true
        }

        return dp[nums.size]
    }
}