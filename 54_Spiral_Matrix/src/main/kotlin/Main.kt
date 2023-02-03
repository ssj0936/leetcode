/*
54. Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun spiralOrder(matrix: Array<IntArray>): List<Int>
}

class Solution:Sol {
    private val dir = arrayOf(Pair(0,1),Pair(1,0),Pair(0,-1),Pair(-1,0))
    private var m:Int = -1
    private var n:Int = -1
    private val PASSED = 5566
    override fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var dirPointer = 0
        var pointer = Pair(0,-1)
        val result = mutableListOf<Int>()
        m = matrix.size
        n = matrix[0].size
        while (dirPointer != -1){
            pointer = Pair(pointer.x() + dir[dirPointer].x(), pointer.y() + dir[dirPointer].y())

            result.add(matrix[pointer.x()][pointer.y()])
            matrix[pointer.x()][pointer.y()] = PASSED

            dirPointer = updateDirection(matrix, pointer, dirPointer)
        }

        return result
    }

    private fun updateDirection(matrix: Array<IntArray>, currPoint:Pair<Int, Int>, currDirPointer:Int):Int{
        var count = 0
        while (count<4){
            val dirPointer = (currDirPointer + count++)%4
            val tmp = Pair(currPoint.x() + dir[dirPointer].x(), currPoint.y() + dir[dirPointer].y())
            if(tmp.x() in 0 until m && tmp.y() in 0 until n && matrix[tmp.x()][tmp.y()] != PASSED)
                return dirPointer
        }
        return -1
    }

    fun Pair<Int,Int>.x() = this.first
    fun Pair<Int,Int>.y() = this.second
}

class Solution01:Sol {
    private val dir = arrayOf(Pair(0,1),Pair(1,0),Pair(0,-1),Pair(-1,0))
    private var m:Int = -1
    private var n:Int = -1
    private var minM:Int = -1
    private var maxM:Int = -1
    private var minN:Int = -1
    private var maxN:Int = -1
    override fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var dirPointer = 0
        var pointer = Pair(0,-1)
        val result = mutableListOf<Int>()
        m = matrix.size
        n = matrix[0].size

        minM = 0
        maxM = m-1
        minN = 0
        maxN = n-1

        while (dirPointer != -1){
            pointer = Pair(pointer.x() + dir[dirPointer].x(), pointer.y() + dir[dirPointer].y())
            result.add(matrix[pointer.x()][pointer.y()])

            dirPointer = updateDirection(pointer, dirPointer)
        }

        return result
    }

    private fun updateDirection(currPoint:Pair<Int, Int>, currDirPointer:Int):Int{
        var count = 0
        var nextDirPointer = -1
        while (count<2){
            val dirPointer = (currDirPointer + count++)%4
            val tmp = Pair(currPoint.x() + dir[dirPointer].x(), currPoint.y() + dir[dirPointer].y())
            if(tmp.x() in minM .. maxM && tmp.y() in minN .. maxN) {
                nextDirPointer = dirPointer
                break
            }
        }

        if(nextDirPointer != currDirPointer){
            when(currDirPointer){
                0-> minM+=1
                1-> maxN-=1
                2-> maxM-=1
                3-> minN+=1
            }
        }

        return nextDirPointer
    }

    fun Pair<Int,Int>.x() = this.first
    fun Pair<Int,Int>.y() = this.second
}