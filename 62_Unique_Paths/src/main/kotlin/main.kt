/*
62. Unique Paths(Medium)

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Example 3:
Input: m = 7, n = 3
Output: 28

Example 4:
Input: m = 3, n = 3
Output: 6

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.

 */

class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        if(m == 1 || n == 1) return 1
        else{
            //初始化為1
            val ground = Array(m){Array(n){1} }
            for(i in 1 until m){
                for(j in 1 until n){
                    ground[i][j] = ground[i-1][j] + ground[i][j-1]
                }
            }
            return ground[m-1][n-1]
        }
    }
}

fun main(args: Array<String>) {
    val result = Solution().uniquePaths(3,3)
    println(result)
}