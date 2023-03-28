/*
200. Number of Islands
Medium
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */

interface Sol{
    fun numIslands(grid: Array<CharArray>): Int
}

class Solution {
    private val direction = arrayOf(
        Pair(-1,0),
        Pair(0,1),
        Pair(1,0),
        Pair(0,-1)
    )

    var m = 0
    var n = 0

    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0

        m = grid.size
        n = grid[0].size

        for(i in 0 until m){
            for(j in 0 until n){
                if(grid[i][j]=='1'){
                    infect(grid, i, j)
                    ++count
                }
            }
        }
        return count
    }

    private fun infect(grid: Array<CharArray>, i:Int, j:Int){
        if(grid[i][j]!='1') return

        grid[i][j] = '3'

        for(dirc in direction){
            val newI = i + dirc.first
            val newJ = j + dirc.second

            if(newI in 0 until m && newJ in 0 until n && grid[newI][newJ]=='1'){
                infect(grid, newI, newJ)
            }
        }
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}