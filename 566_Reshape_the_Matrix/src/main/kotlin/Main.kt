import java.lang.StringBuilder

//566. Reshape the Matrix(Easy)

//In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
//You are given an m x n matrix mat and two integers r and c representing the row number and column number of the wanted reshaped matrix.
//The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
//If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

//Example 1:
//
//
//Input: mat = [[1,2],[3,4]], r = 1, c = 4
//Output: [[1,2,3,4]]
//Example 2:
//
//
//Input: mat = [[1,2],[3,4]], r = 2, c = 4
//Output: [[1,2],[3,4]]
//
//
//Constraints:
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 100
//-1000 <= mat[i][j] <= 1000
//1 <= r, c <= 300

class Solution {
    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        //assume that mat are legal
        //checking element of matrix and r,c are legal or not
        val originalM:Int = mat.size
        val originalN:Int = mat[0].size
        if(originalM * originalN != r * c) return mat

        val newMatrix = Array(r){IntArray(c)}

        var rowIndex = 0
        var colIndex = 0
        for(m in mat){
            for(el in m){
                newMatrix[rowIndex][colIndex++] = el
                if(colIndex >= c){
                    colIndex = 0
                    ++rowIndex
                }
            }
        }

        println("---------")
        println(newMatrix.size)
        println(newMatrix[0].size)

        println(newMatrix[0][0])
        println("---------")
        val s = StringBuilder()
        s.append("[")
        for(i in 0 until newMatrix.size){
            s.append("[")
            for(j in 0 until newMatrix[i].size){
                println(newMatrix[i][j])
                s.append(newMatrix[i][j])
                s.append(",")
            }
            s.append("]")
        }
        s.append("]")
        println(s)
        return newMatrix
    }

}

fun main(args: Array<String>) {
    val mat = arrayOf(
        intArrayOf(1,2),
        intArrayOf(3,4),
    )
    val r = 1
    val c = 4
    val result = Solution().matrixReshape(mat,r,c)
    println(result.toString())
}