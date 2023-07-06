class SolutionOri {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val iSet = mutableSetOf<Int>()
        val jSet = mutableSetOf<Int>()
        val m = matrix.size
        val n = matrix[0].size
        for(i in 0 until m){
            for(j in 0 until n){
                if(matrix[i][j] == 0){
                    iSet.add(i)
                    jSet.add(j)
                }
            }
        }

        for(i in 0 until m){
            for(j in 0 until n) {
                if(iSet.contains(i) || jSet.contains(j))
                    matrix[i][j] = 0
            }
        }
    }
}

class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size
        var isFirstRowZero = false
        var isFirstColumnZero = false

        for (i in 0 until m) {
            for (j in 0 until n) {
                if(matrix[i][j]==0) {
                    if (i == 0) isFirstRowZero = true
                    if (j == 0) isFirstColumnZero = true
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        for (i in 1 until m) {
            for (j in 1 until n) {
                if(matrix[i][0]==0 || matrix[0][j]==0)
                    matrix[i][j] = 0
            }
        }

        if(isFirstRowZero){
            for(j in 0 until n) matrix[0][j] = 0
        }

        if(isFirstColumnZero){
            for(i in 0 until m) matrix[i][0] = 0
        }
    }
}