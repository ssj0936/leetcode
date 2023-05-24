class Solution {
    private val dirs = arrayOf(
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1),
        intArrayOf(-1,0)
    )

    fun shortestBridge(grid: Array<IntArray>): Int {
        val dfsStack = ArrayDeque<IntArray>()
        val bfsQueue = ArrayDeque<IntArray>()
        val visited = 3
        val m = grid.lastIndex
        val n = grid[0].lastIndex

        //find first island
        for (i in 0 .. m){
            if(dfsStack.isNotEmpty()) break

            for(j in 0 .. n){
                if(grid[i][j]==1){
                    dfsStack.addLast(intArrayOf(i,j))
                    break
                }
            }
        }

        //walk through 1st island
        while (dfsStack.isNotEmpty()){
            val pop = dfsStack.removeLast()
            val i = pop[0]
            val j = pop[1]
            grid[i][j] = visited
            bfsQueue.addLast(intArrayOf(i, j))

            for(dir in dirs){
                if(i+dir[0] in 0 ..m && j+dir[1] in 0 .. n && grid[i+dir[0]][j+dir[1]] == 1)
                    dfsStack.addLast(intArrayOf(i+dir[0], j+dir[1]))
            }
        }
        //BFS find 2nd island
        var level = 0
        while (bfsQueue.isNotEmpty()){
            val size = bfsQueue.size
            repeat(size) {
                val pop = bfsQueue.removeFirst()
                val i = pop[0]
                val j = pop[1]
                for(dir in dirs){
                    if(i+dir[0] in 0 ..m && j+dir[1] in 0 .. n){
                        if(grid[i+dir[0]][j+dir[1]] == 0) {
                            grid[i+dir[0]][j+dir[1]] = visited
                            bfsQueue.addLast(intArrayOf(i + dir[0], j + dir[1]))
                        }
                        else if(grid[i+dir[0]][j+dir[1]] == 1)
                            return level
                    }
                }
            }
            ++level
        }
        return level
    }
}