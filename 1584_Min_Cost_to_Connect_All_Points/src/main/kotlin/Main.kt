import java.util.PriorityQueue

class Solution {
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        //mapping to dist of node1 and node2
        val dist = Array(n){IntArray(n)}

        //intArray[node1Index, node2Index]
        val maxHeap = PriorityQueue<IntArray>(compareBy { -dist[it[0]][it[1]]})
        for(i in points.indices){
            for(j in i+1 .. points.lastIndex){
                getDist(points[i], points[j]).also {
                    dist[i][j] = it
                    maxHeap.add(intArrayOf(i, j))
                }
            }
        }

        val parent = IntArray(n){it}
        fun getRoot(node:Int):Int{
            if(parent[node] == node)
                return node

            parent[node] = getRoot(parent[node])
            return parent[node]
        }

        fun union(node1:Int, node2:Int){
            val root1 = getRoot(node1)
            val root2 = getRoot(node2)
            parent[root1] = root2
        }

        var edgeCount = n-1
        var cost = 0
        while (edgeCount>0){
            val edge = maxHeap.remove()
            if(getRoot(edge[0]) != getRoot(edge[1])){
                union(edge[0], edge[1])
                cost += dist[edge[0]][edge[1]]
                --edgeCount
            }
        }

        return cost
    }

    private fun getDist(p1:IntArray, p2:IntArray):Int{
        return ((p1[0] - p2[0]) * if(p1[0]>p2[0]) 1 else -1) + ((p1[1] - p2[1]) * if(p1[1]>p2[1]) 1 else -1)
    }
}