class Solution {
    fun maxCoins(nums: IntArray): Int {
        val n = nums.size
        val dp = Array(n){IntArray(n)}

        for(i in nums.indices){
            for(j in i downTo 0){
                for (k in j..i) {
                    var m = nums[k]
                    if (j - 1 >= 0) m *= nums[j - 1]
                    if (i + 1 <= n - 1) m *= nums[i + 1]
                    if (k + 1 <= i) m += dp[k + 1][i]
                    if (j <= k - 1) m += dp[j][k - 1]

                    dp[j][i] = maxOf(m, dp[j][i])
                }
            }
        }

        return dp[0][n-1]
    }
}