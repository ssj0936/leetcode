import java.util.*

/**
 * 48. Rotate Image
Medium
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:
https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val matrix = arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9))
    val s = Solution()
    s.rotate(matrix)
}

interface Sol{
    fun rotate(matrix: Array<IntArray>): Unit
}


/*
思路是這樣：先對角線翻轉，也就是m[i][j], m[j][i]對調
再左右翻轉，也就是m[i][j], n[i][(n-1)-j]
建議是直接拿一張正方型的紙實際操練一次，直覺又好懂

注意的地方有兩個：
1. 翻轉的時候記得不要loop整個matrix，會等於翻兩次跟沒翻一樣
2. 頭尾對調的邊際條件
 */
class Solution:Sol {
    override fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        for(i in 0 until n){
            for(j in i until n){
                if(i == j) continue

                val tmp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = tmp
            }
        }

        println(matrix.contentDeepToString())

        for(row in matrix){
            for(i in 0 .. (n/2)){
                val tmp = row[i]
                row[i] = row[n-1-i]
                row[n-1-i] = tmp
            }
        }
    }
}