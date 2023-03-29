import java.util.*

/*
542. 01 Matrix
Medium
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 10^4
1 <= m * n <= 10^4
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.

 */

interface Sol{
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray>
}

class Solution:Sol {
    private val directions = arrayOf(Pair(-1,0),Pair(0,1),Pair(1,0),Pair(0,-1))
    private var m = 0
    private var n = 0
    override fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        m = mat.size
        n = mat[0].size

        for (i in mat.indices){
            for(j in mat[i].indices){
                mat[i][j] *= -1
            }
        }

        for (i in mat.indices){
            for(j in mat[i].indices){
                if(mat[i][j]!=0)
                    mat[i][j] = getMinDistance(mat, i, j, 0)
            }
        }

        return mat
    }
    private fun getMinDistance(mat:Array<IntArray>, i:Int, j:Int, count:Int):Int{
        val queue = LinkedList<Pair<Int, Int>>().apply {
            offer(Pair(i, j))
        }
        val table = mutableSetOf<String>().apply {
            add(Pair(i, j).toString())
        }
        var distance = -1
        while (queue.isNotEmpty()) {
            ++distance
            val size = queue.size
            repeat(size) {
                val node = queue.poll()
                for (direction in directions) {
                    val newI = node.first + direction.first
                    val newJ = node.second + direction.second
                    if (newI in 0 until m && newJ in 0 until n ) {
                        if(mat[newI][newJ]==0) return distance+1

                        //showed
                        if(table.contains(Pair(newI,newJ).toString())) continue

                        if(mat[newI][newJ]!=0){
                            queue.offer(Pair(newI,newJ))
                            table.add(Pair(newI,newJ).toString())
                        }
                    }
                }
            }
        }
        return -1
    }
}

class SolutionBFS:Sol {
    private val directions = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    private var m = 0
    private var n = 0
    override fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        m = mat.size
        n = mat[0].size

        val queue = LinkedList<Pair<Int,Int>>()

        for (i in mat.indices){
            for(j in mat[i].indices){
                if(mat[i][j]==0 && isOneAttached(mat,i,j))
                    queue.offer(Pair(i,j))

                mat[i][j] *= -1
            }
        }

        var distance = 0
        while (queue.isNotEmpty()){
            val size = queue.size
            ++distance
            repeat(size){
                val node = queue.poll()
                val i = node.first
                val j = node.second

                for(direction in directions){
                    val newI = i + direction.first
                    val newJ = j + direction.second
                    if (newI in 0 until m && newJ in 0 until n && mat[newI][newJ]==-1){
                        queue.offer(Pair(newI, newJ))
                        mat[newI][newJ] = distance
                    }
                }
            }
        }
        return mat
    }

    private fun isOneAttached(mat: Array<IntArray>, i:Int, j:Int):Boolean{
        for(direction in directions){
            val newI = i + direction.first
            val newJ = j + direction.second
            if (newI in 0 until m && newJ in 0 until n && mat[newI][newJ]!=0)
                return true
        }
        return false
    }
}

class SolutionAmazing:Sol {
    private val directions = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    private var m = 0
    private var n = 0
    override fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val result = Array(mat.size){IntArray(mat[0].size){10001} }

        for (i in mat.indices){
            for(j in mat[i].indices){
                result[i][j] = if(mat[i][j]==0) 0 else result[i][j]
            }
        }

        for(i in 0 .. mat.lastIndex){
            for(j in 0 .. mat[i].lastIndex){
                if(i>0)
                    result[i][j] = Math.min(result[i][j], result[i-1][j]+1)
                if(j>0)
                    result[i][j] = Math.min(result[i][j], result[i][j-1]+1)
            }
        }

        for(i in mat.lastIndex downTo 0){
            for(j in mat[i].lastIndex downTo 0){
                if(i<mat.lastIndex)
                    result[i][j] = Math.min(result[i][j], result[i+1][j]+1)
                if(j<mat[i].lastIndex)
                    result[i][j] = Math.min(result[i][j], result[i][j+1]+1)
            }
        }

        return result
    }
}


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}