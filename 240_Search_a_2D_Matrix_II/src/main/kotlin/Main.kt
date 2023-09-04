class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if(target !in matrix.first().first() .. matrix.last().last()) return false

        fun getBound(matrix: Array<IntArray>, target: Int, getFloor:Boolean):Int{
            var left = 0
            var right = matrix.first().lastIndex

            while (left<=right){
                val mid = left + (right - left)/2
                if(target in matrix.first()[mid] .. matrix.last()[mid]) {
                    if(getFloor)
                        right = mid - 1
                    else
                        left = mid +1
                }
                else if(target < matrix.first()[mid])
                    right = mid -1
                else
                    left = mid +1
            }
            return left
        }

        val twoDFloor = getBound(matrix, target, true)
        val twoDCeiling = getBound(matrix, target, false) - 1

        fun get1DFloor(matrix: Array<IntArray>, target: Int, start:Int, end:Int):Int{
            var left = 0
            var right = matrix.size
            while (left<=right){
                val mid = left + (right - left)/2
                if(target in matrix[mid][start]..matrix[mid][end])
                    right = mid -1
                else if(target < matrix[mid][start])
                    right = mid -1
                else
                    left = mid +1
            }
            return left
        }

        val oneDFloor = get1DFloor(matrix, target, twoDFloor, twoDCeiling)

        for(i in oneDFloor .. matrix.lastIndex){
            if(target !in matrix[i][twoDFloor]..matrix[i][twoDCeiling]) break

            if(matrix[i].binarySearch(target, twoDFloor, twoDCeiling+1)>=0) return true
        }

        return false
    }
}

class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var x = matrix.lastIndex
        var y = 0


        while (x>=0 && y<=matrix[0].lastIndex){
            if(target == matrix[x][y]) return true

            if(target < matrix[x][y]) --x
            else ++y
        }
        return false
    }
}