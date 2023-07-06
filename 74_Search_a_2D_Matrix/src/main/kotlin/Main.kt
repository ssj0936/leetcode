class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val firstResult = binarySearch(matrix, target)
        if(firstResult<0) return false

        val secondResult = matrix[firstResult].binarySearch(target)
        return secondResult>=0
    }

    private fun binarySearch(matrix: Array<IntArray>, target: Int):Int{
        val width = matrix[0].lastIndex
        var left = 0
        var right = matrix.lastIndex
        while (left<=right){
            val mid = (left+right)/2

            if(target > matrix[mid][width])
                left = mid+1
            else if(target < matrix[mid][0])
                right = mid -1
            else
                return mid
        }

        return -(left+1)
    }
}