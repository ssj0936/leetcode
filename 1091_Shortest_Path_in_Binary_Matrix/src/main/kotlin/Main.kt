import java.util.LinkedList

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

class Solution{

    private val dirs = arrayOf(
        intArrayOf(1,1),intArrayOf(-1,1),intArrayOf(1,-1),intArrayOf(-1,-1),
        intArrayOf(0,1),intArrayOf(1,0),intArrayOf(-1,0),intArrayOf(0,-1)
    )


    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        val n = grid.size
        if(grid[0][0]==1 || grid[n-1][n-1]==1)
            return -1

        val queue = LinkedList<IntArray>().apply {
            offer(intArrayOf(0,0))
        }

        var step = 0
        while (queue.isNotEmpty()){
            val size = queue.size
            ++step
            repeat(size){
                val pop = queue.poll()

                if(pop[0]==n-1 && pop[1]==n-1)
                    return step

                for(dir in dirs) {
                    val x = pop[0] + dir[0]
                    val y = pop[1] + dir[1]
                    if ((x in 0..n - 1) && (y in 0..n - 1) && grid[x][y] == 0) {
                        grid[x][y] = -1
                        queue.offer(intArrayOf(x,y))
                    }
                }
            }
        }

        return -1
    }
}
