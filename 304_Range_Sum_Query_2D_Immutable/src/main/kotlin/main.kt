/*
304. Range Sum Query 2D - Immutable(Medium)
Given a 2D matrix matrix, handle multiple queries of the following type:
Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example 1:
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-10^5 <= matrix[i][j] <= 10^5
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 10^4 calls will be made to sumRegion.

 */

class NumMatrix(private val matrix: Array<IntArray>) {

    //暴力解 但因為At most 10^4 calls will be made to sumRegion.會超時
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = 0
        for(i in row1 .. row2){
            for(j in col1 .. col2){
                sum += matrix[i][j]
            }
        }
        return sum
    }

    //第二種解，比較好的方法，用DP把(0,0)到(x,y)加總存在dp[i][j]裡
    //算sumRegion的時候只需要看成區域切割就好，只需要O(1)
    val hashMap = HashMap<Pair<Int,Int>,Int>().apply {
        put(Pair(0,0),matrix[0][0])
    }
    init{
        for(i in matrix.indices){
            for(j in matrix[i].indices){
                if(i==0 && j==0) continue

                else if(i == 0)
                    hashMap.put(Pair(i,j),hashMap[Pair(i,j-1)]!!.plus(matrix[i][j]))
                else if(j == 0)
                    hashMap.put(Pair(i,j),hashMap[Pair(i-1,j)]!!.plus(matrix[i][j]))
                else
                    hashMap.put(Pair(i,j),hashMap[Pair(i,j-1)]!!.plus(hashMap[Pair(i-1,j)]!!).minus(hashMap[Pair(i-1,j-1)]!!).plus(matrix[i][j]))

//                println("$i,$j:${hashMap.getValue(Pair(i,j))}")
            }
        }
    }

    fun sumRegionV2(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return hashMap.get(Pair(row2,col2))!! - (hashMap.get(Pair(row2,col1-1))?:0) - (hashMap.get(Pair(row1-1,col2))?:0) + (hashMap.get(Pair(row1-1,col1-1))?:0)
    }
    //------------------------------------

    /*
    本來以為hashmap會快，但以pair當作是key不是一個好方法，會拖慢速度，不如直接用2D array
     */

    /*
    一個小細節，dp的size加大一級
    因為最後在區域切割時，起點是要exclusive的，既然如此在創DP時就直接拉大一格，否則再算exceedLimit的時候會很難處理
    等於是把原矩陣 往右下移動，左邊上面補0
     */

    val dp = Array(matrix.size+1){IntArray(matrix[0].size+1){0} }
    init{
        for(i in matrix.indices){
            for(j in matrix[i].indices){
                dp[i+1][j+1] = dp[i+1][j] + dp[i][j+1] - dp[i][j] + matrix[i][j]
            }
        }
    }
    fun sumRegionV3(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1]

//        return hashMap.get(Pair(row2,col2))!! - (hashMap.get(Pair(row2,col1-1))?:0) - (hashMap.get(Pair(row1-1,col2))?:0) + (hashMap.get(Pair(row1-1,col1-1))?:0)
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * var obj = NumMatrix(matrix)
 * var param_1 = obj.sumRegion(row1,col1,row2,col2)
 */
fun main(args: Array<String>) {
    var obj = NumMatrix(arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    ))
//    var param_1 = obj.sumRegionV2(2, 1, 4, 3)
    var param_1 = obj.sumRegionV3(0, 0, 4, 3)
    println(param_1)
}