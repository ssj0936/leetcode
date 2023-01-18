import java.util.*

/*
994. Rotting Oranges
Medium
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun orangesRotting(grid: Array<IntArray>): Int
}

class Solution:Sol {
    val EMPTY = 0
    val FRESH = 1
    val ROTTEN = 2

    var count = 0

    var m:Int = -1
    var n:Int = -1

    override fun orangesRotting(grid: Array<IntArray>): Int {
        m = grid.size
        n = grid[0].size
        var isEmpty = true

        //放rotten orange的座標
        var queue = mutableSetOf<Pair<Int,Int>>()
        for(i in grid.indices){
            for(j in grid[i].indices){
                if(grid[i][j] == ROTTEN)
                    queue.add(Pair(i, j))
                if(grid[i][j] != EMPTY)
                    isEmpty = false
            }
        }

        bfs(grid, queue)

        for(i in grid.indices){
            for(j in grid[i].indices){
                if(grid[i][j] == FRESH)
                    return -1
            }
        }

        return count
    }

    private fun bfs(grid: Array<IntArray>, queue:Set<Pair<Int,Int>>){
        if(queue.isEmpty())
            return

        val newQueue = mutableSetOf<Pair<Int,Int>>()
        for(rotten in queue){

            if(isValidNode(rotten.first-1, rotten.second) && grid[rotten.first-1][rotten.second]==FRESH) {
                newQueue.add(Pair(rotten.first - 1, rotten.second))
                grid[rotten.first - 1][rotten.second] = 2
            }
            if(isValidNode(rotten.first, rotten.second-1) && grid[rotten.first][rotten.second-1]==FRESH) {
                newQueue.add(Pair(rotten.first, rotten.second - 1))
                grid[rotten.first][rotten.second - 1] = 2
            }
            if(isValidNode(rotten.first+1, rotten.second) && grid[rotten.first+1][rotten.second]==FRESH) {
                newQueue.add(Pair(rotten.first + 1, rotten.second))
                grid[rotten.first+ 1][rotten.second] = 2

            }
            if(isValidNode(rotten.first, rotten.second+1) && grid[rotten.first][rotten.second+1]==FRESH) {
                newQueue.add(Pair(rotten.first, rotten.second + 1))
                grid[rotten.first][rotten.second+ 1] = 2
            }
        }

        if(newQueue.isNotEmpty())
            ++count

        bfs(grid, newQueue)
    }

    private fun isValidNode(i:Int, j:Int):Boolean{
        return i in 0 until m && j in 0 until n
    }
}

class Solution02:Sol {
    val EMPTY = 0
    val FRESH = 1
    val ROTTEN = 2


    var m:Int = -1
    var n:Int = -1
    var fourDirection = listOf(
        Pair(-1,0),Pair(+1,0),Pair(0,-1),Pair(0,+1),
    )

    override fun orangesRotting(grid: Array<IntArray>): Int {
        m = grid.size
        n = grid[0].size
        var freshCount = 0
        var count = 0

        //放rotten orange的座標
        var queue = LinkedList<Pair<Int,Int>>()
        for(i in grid.indices){
            for(j in grid[i].indices){
                when(grid[i][j]){
                    FRESH->
                        ++freshCount
                    ROTTEN->
                        queue.add(Pair(i, j))
                }
            }
        }

        while (queue.isNotEmpty() && freshCount>0) {
            ++count

            val countDown = queue.size
            repeat(countDown) {
                val rotten = queue.poll()

                for(dir in fourDirection){
                    val node = Pair(rotten.first+dir.first, rotten.second+dir.second)
                    if(isValidNode(node.first, node.second) && grid[node.first][node.second]==FRESH){
                        grid[node.first][node.second] = ROTTEN
                        queue.add(node)
                        --freshCount
                    }
                }
            }
        }

        return if(freshCount!=0) -1 else count
    }

    private fun isValidNode(i:Int, j:Int):Boolean{
        return i in 0 until m && j in 0 until n
    }
}