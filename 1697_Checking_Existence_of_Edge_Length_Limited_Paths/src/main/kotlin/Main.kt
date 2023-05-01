fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray
}

class SolutionUnion:Sol {
    override fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
        val result = BooleanArray(queries.size)
        val father = IntArray(n){it}
        fun findRoot(node:Int):Int{
            if(father[node] == node)
                return node

            father[node] = findRoot(father[node])
            return father[node]
        }

        fun union(node1:Int, node2:Int){
            val root1 = findRoot(node1)
            val root2 = findRoot(node2)
            if(root1<root2)
                father[root2] = root1
            else
                father[root1] = root2
        }
        val sortedEdgeList = edgeList.sortedBy { it[2] }
        var sortedQueriesIndex = queries.indices.sortedBy { queries[it][2] }

        var index = 0
        for(queryIndex in sortedQueriesIndex){
            val query = queries[queryIndex]
            val queryNode1 = query[0]
            val queryNode2 = query[1]
            val dist = query[2]
            while (index<sortedEdgeList.size && sortedEdgeList[index][2] < dist){
                val node1 = sortedEdgeList[index][0]
                val node2 = sortedEdgeList[index][1]
                if(findRoot(node1) != findRoot(node2)){
                    union(node1, node2)
                }
                ++index
            }
            result[queryIndex] = (findRoot(queryNode1) == findRoot(queryNode2))

        }
        return result

    }
}

class Solution:Sol {
    override fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
        val connection = mutableMapOf<Int, MutableMap<Int,Int>>()
        for(edge in edgeList){
            val node1 = edge[0]
            val node2 = edge[1]
            val distance = edge[2]

            val value1 = connection.getOrPut(node1){mutableMapOf()}.getOrPut(node2){distance}
            connection.get(node1)?.put(node2, minOf(value1, distance))

            val value2 = connection.getOrPut(node2){mutableMapOf()}.getOrPut(node1){distance}
            connection.get(node2)?.put(node1, minOf(value2, distance))
        }


        val result = BooleanArray(queries.size)
        for(i in queries.indices){
            if(connection.get(queries[i][0])==null || connection.get(queries[i][1])==null)
                result[i] = false
            else {
                val subResult = bfs(queries[i][0], queries[i][1], queries[i][2], mutableSetOf(), connection)
                result[i] = subResult
            }
        }
        return result
    }

    fun bfs(start:Int, target:Int, limit:Int, visited:MutableSet<Int>, connection:Map<Int,Map<Int,Int>>):Boolean{
        visited.add(start)
        for(entry in connection.get(start)!!){
            if(visited.contains(entry.key)) continue
            if(entry.value>limit) continue

            if(entry.key == target && entry.value<limit) {
                return true
            }
            if(bfs(entry.key, target, limit, visited.toMutableSet(), connection)) {
                return true
            }
        }
        return false
    }
}