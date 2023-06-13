fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    private val dirs = arrayOf(
        intArrayOf(-1,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1)
    )

    private var m:Int = 0
    private var n:Int = 0

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        m = heights.size
        n = heights[0].size
        val result = mutableListOf<List<Int>>()
        val visitablePacific = Array(m){BooleanArray(n)}
        val visitableAtlantic = Array(m){BooleanArray(n)}

        //row
        for(i in 0 until m){
            dfsHelper(heights, i, 0, visitablePacific)
            dfsHelper(heights, i, n-1, visitableAtlantic)
        }

        //column
        for(i in 0 until n){
            dfsHelper(heights, 0, i, visitablePacific)
            dfsHelper(heights, m-1, i, visitableAtlantic)
        }

        for(i in 0 until m){
            for(j in 0 until n){
                if(visitablePacific[i][j] && visitableAtlantic[i][j])
                    result.add(listOf(i,j))
            }
        }
        return result
    }

    private fun dfsHelper(
        heights: Array<IntArray>,
        i: Int,
        j: Int,
        visited: Array<BooleanArray>,
    ){
        visited[i][j] = true
        for (dir in dirs) {
            val x = i + dir[0]
            val y = j + dir[1]
            if((x in 0 until m) && (y in 0 until n) && !visited[x][y] && heights[i][j]<=heights[x][y])
                dfsHelper(heights, x, y, visited)
        }
    }
}


class SolutionOri {
    private val dirs = arrayOf(
        intArrayOf(-1,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1)
    )

    private val PACIFIC = 0
    private val ATLANTIC = 1
    private var m:Int = 0
    private var n:Int = 0
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        m = heights.size
        n = heights[0].size
        val result = mutableListOf<List<Int>>()
        val reach = Array(m){Array(n){BooleanArray(2)}}

        for(i in 0 until m){
            for(j in 0 until n){
                val r = dfsHelper(heights, i, j, reach, Array(m){ BooleanArray(n) }, i, j)
                reach[i][j][PACIFIC] = r[PACIFIC]
                reach[i][j][ATLANTIC] = r[ATLANTIC]

                if(r[PACIFIC] && r[ATLANTIC])
                    result.add(listOf(i,j))
            }
        }

        return result
    }

    private fun dfsHelper(
        heights: Array<IntArray>,
        i:Int, j:Int,
        reach:Array<Array<BooleanArray>>,
        visited:Array<BooleanArray>,
        oriI:Int, oriJ:Int
    ):BooleanArray{
        var pacific = ((j==0) || (i==0))
        var atlantic = ((j==n-1) || (i == m-1))
        visited[i][j] = true

        for(dir in dirs){
            val x = i + dir[0]
            val y = j + dir[1]
            if((x in 0 until m) && (y in 0 until n) && heights[i][j]>=heights[x][y]){
                if(visited[x][y]){
                    pacific = (pacific || reach[x][y][PACIFIC])
                    atlantic = (atlantic || reach[x][y][ATLANTIC])
                }else{
                    val result:BooleanArray =
                        if(x>oriI || y>oriJ)
                            dfsHelper(heights, x, y, reach,visited, oriI, oriJ)
                        else
                            reach[x][y]

                    pacific = (pacific || result[PACIFIC])
                    atlantic = (atlantic || result[ATLANTIC])
                }
            }
        }

        return booleanArrayOf(pacific, atlantic)
    }
}