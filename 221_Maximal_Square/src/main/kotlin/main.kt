/*
221. Maximal Square(Medium)

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Example 2:
Input: matrix = [["0","1"],["1","0"]]
Output: 1

Example 3:
Input: matrix = [["0"]]
Output: 0

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */

class Solution {
    fun maximalSquare(matrix: Array<CharArray>): Int {

        val dp = Array(matrix.size){IntArray(matrix[0].size){-1} }
        var max = Int.MIN_VALUE

        for(i in matrix.indices){
            for (j in matrix[0].indices){
                val thisNumber:Int = matrix[i][j]-'0'
                var dpValue = 0
                if(i == 0 || j == 0) dpValue = thisNumber
                else if(thisNumber==1){
                    dpValue = 1
                    val leftUp = dp[i-1][j-1]
                    if(leftUp>0){
                        for(k in 1 .. leftUp){
                            if((matrix[i-k][j] == '1') && (matrix[i][j-k] == '1')){
                                dpValue++
                            }else
                                break
                        }
                    }
                }

                dp[i][j] = dpValue

                if(dp[i][j] > max) max = dp[i][j]
            }
        }

        for(i in matrix){
            println(i.contentToString())
        }
        println("-------------")
        for(i in dp){
            println(i.contentToString())
        }

        return max*max
    }
}

fun main(args: Array<String>) {
    val matrix = arrayOf(
        charArrayOf('1','0','1','0','0'),
        charArrayOf('1','0','1','1','1'),
        charArrayOf('1','1','1','1','1'),
        charArrayOf('1','0','0','1','0')
//        charArrayOf('0','0','0','1'),
//        charArrayOf('1','1','0','1'),
//        charArrayOf('1','1','1','1'),
//        charArrayOf('0','1','1','1'),
//        charArrayOf('0','1','1','1')

    )
    val result = Solution().maximalSquare(matrix)

    println(result)
}