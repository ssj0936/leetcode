/**
 * 322. Coin Change
Medium
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun coinChange(coins: IntArray, amount: Int): Int
}
/*
DP解，dp[i]代表amount為i的最少換幣數
dp[i] = min(dp[i-coins[0]], dp[i-coins[1]],...dp[i-coins[n]]) +1
簡單的以例子來說 coins = [1,2,5] amount = 5
dp[5] = min(dp[5-1], dp[5-2]) +1 = min(dp[4], dp[3]) +1
因為4補上1塊錢硬幣 和 3補上2塊錢硬幣，都能成為5，那就選一個最小的，再加上一顆新的硬幣
 */
class Solution:Sol {
    override fun coinChange(coins: IntArray, amount: Int): Int {
        if(amount == 0) return 0

        val dp = Array<Int>(amount+1){-1}
        dp[0] = 0
        for(i in 1 .. amount){
            var min = Int.MAX_VALUE
            //這個flag用來表示 往前有找到更小的換幣單位
            var flag = false
            for(coin in coins){
                //如果dp[i-coin] != -1，代表沒辦法為i-coin換幣，所以可以略過這個選項
                if(i-coin>=0 && dp[i-coin] != -1 && dp[i-coin]<min) {
                    min = dp[i - coin]
                    flag = true
                }
            }

            //往前都沒有找到 那就維持-1
            if(flag)
                dp[i] = 1+min
        }
        return dp[amount]
    }
}