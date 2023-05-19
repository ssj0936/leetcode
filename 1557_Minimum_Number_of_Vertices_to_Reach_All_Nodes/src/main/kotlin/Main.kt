fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        val graph = Array<MutableSet<Int>>(n){ mutableSetOf()}
        for(edge in edges){
            val from  = edge[0]
            val to = edge[1]
            graph[to].add(from)
        }

        val result = mutableListOf<Int>()
        for(i in graph.indices){
            if(graph[i].isEmpty())
                result.add(i)
        }

        return result
    }
}

class Solution2 {
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        val graph = IntArray(n)
        for(edge in edges){
            val to = edge[1]
            ++graph[to]
        }

        val result = mutableListOf<Int>()
        for(i in graph.indices){
            if(graph[i]==0)
                result.add(i)
        }

        return result
    }
}