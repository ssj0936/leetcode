import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution{
    var max = Int.MIN_VALUE
    var m: Int = 0
    var n: Int = 0
    private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        m = matrix.size
        n = matrix[0].size
        val dp = Array(m){IntArray(n)}

        for(i in 0 until m){
            for(j in 0 until n){
                dfsHelper(matrix, dp, i, j)
            }
        }
        return max
    }

    private fun dfsHelper(matrix: Array<IntArray>, dp:Array<IntArray>, i:Int, j:Int):Int{
        if(dp[i][j]!=0) return dp[i][j]

        val value = matrix[i][j]
        var m = 0
        for(dir in dirs){
            if(i+dir.first in 0 until this.m && j+dir.second in 0 until n && matrix[i+dir.first][j+dir.second] > value){
                val result = dfsHelper(matrix, dp, i+dir.first, j+dir.second)
                m = maxOf(m, result)
            }
        }
        dp[i][j] = m + 1
        max = maxOf(max, dp[i][j])
        return dp[i][j]
    }
}

class SolutionBFS {
    var max = Int.MIN_VALUE
    var m:Int = 0
    var n:Int = 0
    private val dirs = arrayOf(Pair(-1,0),Pair(0,1),Pair(1,0),Pair(0,-1))
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        m = matrix.size
        n = matrix[0].size
        val dp = Array(m){IntArray(n)}

        for(i in 0 until m){
            for(j in 0 until n){
                bfsHelper(matrix, dp, i, j)
            }
        }

        return max
    }

    private fun bfsHelper(matrix: Array<IntArray>, dp:Array<IntArray>, i:Int, j:Int){
        if(dp[i][j]!=0) return

        val queue = LinkedList<Pair<Int, Int>>().apply { offer(i to j) }
        var count = 1
        while (queue.isNotEmpty()){
            val size = queue.size
            repeat(size) {
                val pop = queue.poll()
                val x = pop.first
                val y = pop.second
                val value = matrix[x][y]
                dp[x][y] = count
                for(dir in dirs){
                    if(x+dir.first in 0 until m && y+dir.second in 0 until n
                        && matrix[x+dir.first][y+dir.second] > value
                        && dp[x+dir.first][y+dir.second]!=-1 && dp[x+dir.first][y+dir.second] <= count
                    ){
                        dp[x+dir.first][y+dir.second] = -1
                        queue.offer(Pair(x+dir.first, y+dir.second))
                    }
                }
            }
            max = maxOf(max, count)
            ++count
        }
    }
}