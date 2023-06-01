class Solution{

    private val dirs = arrayOf(
        intArrayOf(1, 1), intArrayOf(-1, 1), intArrayOf(1, -1), intArrayOf(-1, -1),
        intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1)
    )

    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        val n = grid.size
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1

        dfs(grid, -1, -1, 0, n)
        return if(grid[n-1][n-1]==0) -1 else grid[n-1][n-1]*-1
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int, count: Int, n: Int) {
        for(dir in dirs){
            val x = i + dir[0]
            val y = j + dir[1]
            if((x in 0 .. n-1) && (y in 0 ..n-1) && (grid[x][y]==0 ||grid[x][y]<count-1)){
//                println("grid[$x][$y] = ${1-count}")
                grid[x][y] = count-1
                dfs(grid, x, y, count-1, n)
            }
        }

    }
}

class SolutionBFS {
//    private val dirs = arrayOf(
//        intArrayOf(-1,0),8
//        intArrayOf(-1,1),9
//        intArrayOf(0,1),6
//        intArrayOf(1,1),3
//        intArrayOf(1,0),2
//        intArrayOf(1,-1),1
//        intArrayOf(0,-1),4
//        intArrayOf(-1,-1)7
//    )

    private val dirs = arrayOf(
        intArrayOf(1,1),intArrayOf(-1,1),intArrayOf(1,-1),intArrayOf(-1,-1),
        intArrayOf(0,1),intArrayOf(1,0),intArrayOf(-1,0),intArrayOf(0,-1)
    )

    var step = 0
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        val n = grid.size
        if(grid[0][0]==1 || grid[n-1][n-1]==1)
            return -1

        bfs(grid, -1,-1,0,n)
        return step
    }

    private fun bfs(grid: Array<IntArray>, i:Int, j:Int, count:Int, n:Int){
        if(step!=0)
            return

        if(i == n-1 && j == n-1) {
            step = count*-1
            return
        }

        for(dir in dirs){
            val x = i + dir[0]
            val y = j + dir[1]
            if((x in 0 .. n-1) && (y in 0 ..n-1) && grid[x][y]==0){
                println("grid[$x][$y] = ${1-count}")
                grid[x][y] = count-1
                bfs(grid, x, y, count-1, n)
            }
        }
    }
}
