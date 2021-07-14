import java.lang.StringBuilder

/*
63. Unique Paths II(Medium)
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and space is marked as 1 and 0 respectively in the grid.

Example 1:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 */

class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        if(obstacleGrid[0][0]==1) return 0

        translate(obstacleGrid)
        if(m == 1 || n == 1) return obstacleGrid[m-1][n-1]
        else{
            //作法完全一樣，但遇到障礙物就直接變0
            for(i in 1 until m){
                for(j in 1 until n){
                    if(obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0
                    else obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1]
                }
            }
            return obstacleGrid[m-1][n-1]
        }
    }

    //轉譯

    //用原本的陣列來計算節省空間，和前一題一樣，先處理第一行和第一列
    //沒有障礙物的話 每一格應該都是1，多了障礙物的影響是，只要出現障礙物之後就開始為0
    //這個fun在做的就是這個
    //最後要把[0][0]設成1 因應[[0]]這個input
    private fun translate(obstacleGrid: Array<IntArray>){
        val m = obstacleGrid.size
        var bumped = false
        for(i in 1 until m){
            obstacleGrid[i][0] = if(bumped || obstacleGrid[i][0]==1){
                bumped = true
                0
            }else 1
        }

        val n = obstacleGrid[0].size
        bumped = false
        for(i in 1 until n){
            obstacleGrid[0][i] = if(bumped || obstacleGrid[0][i]==1){
                bumped = true
                0
            }else 1
        }
        obstacleGrid[0][0] = 1
    }
}
fun main(args: Array<String>) {
    val input = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,1,0),
        intArrayOf(0,0,0)
    )

    print2DArray(input)

//    println(input.contentToString())
    val result = Solution().uniquePathsWithObstacles(input)


    println(result)
}

fun print2DArray(array: Array<IntArray>){
    val sb = StringBuilder()
    for(i in array.indices){
        for(j in array[0].indices){
            sb.append(array[i][j])
            sb.append(",")
        }
        sb.append("\n")
    }
    println(sb)
}