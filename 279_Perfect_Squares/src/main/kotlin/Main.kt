/*
279. Perfect Squares(Medium)
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

Constraints:
1 <= n <= 10^4

 */
interface Sol {
    fun numSquares(n: Int): Int
}

class Solution:Sol {
    //dp[i]代表i的最小完平方數個數
    //dp[i] = 1 + min()
    override fun numSquares(n: Int): Int {
        val dp = IntArray(n+1){0}.apply {
            this[1] = 1
        }

        /*
        簡單的說，dp[i]一定會是 1 + dp[(i-某個比i小的數字的平方)]
         */
        for(i in 1 .. n){
            var min = Int.MAX_VALUE

            //從0開始找
            var tmp = 0
            while(tmp*tmp <= i){
                ++tmp

                val square = tmp*tmp
                if(i-square<0) break

                if(dp[i-square] < min)
                    min = dp[i-square]
            }
            dp[i] = 1 + min
        }
        return dp[n]
    }
}
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}