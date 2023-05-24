fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}
interface Sol{
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray
}

class Solution:Sol {
    override fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val graph = mutableMapOf<String, MutableMap<String, Double>>()
        for(i in equations.indices){
            //equations[i][0] = values[i] * equations[i][1]
            graph.getOrPut(equations[i][0]){ mutableMapOf() }.apply {
                put(equations[i][1], values[i])
                put(equations[i][0], 1.0)
            }

            graph.getOrPut(equations[i][1]){ mutableMapOf() }.apply {
                put(equations[i][0], 1/values[i])
                put(equations[i][1], 1.0)
            }
        }

        println("graph:$graph")

        val result = DoubleArray(queries.size)
        for((index,query) in queries.withIndex()){
            println("query:$query")

            var subResult:Double = -1.0

            if(graph.get(query[0])!=null && graph.get(query[1])!=null) {
                if (query[0] == query[1])
                    subResult = 1.0
                else{
                    findRoot(graph, query[0])
                    findRoot(graph, query[1])
                    for (i in graph.get(query[0])!!) {
                        for (j in graph.get(query[1])!!) {
                            if (i.key == j.key) {
                                subResult = i.value / j.value
                            }
                        }
                    }
                }
            }
//            println("subresult:$subResult")
            result[index] = subResult
        }

        return result
    }

    private fun findRoot(graph:MutableMap<String, MutableMap<String, Double>>, node:String){
        val newMap = mutableMapOf<String, Double>()
        val queue = ArrayDeque<Pair<String, Double>>().apply { addLast(node to 1.0) }

        while (queue.isNotEmpty()){
            val pop:Pair<String, Double> = queue.removeFirst()
            val n = pop.first
            val multiplier = pop.second

            for (parent in graph.get(n)!!) {
                //self is root
                if(parent.key == n)
                    newMap.put(parent.key, multiplier)
                else{
                    queue.addLast(parent.key to parent.value * multiplier)
                }
            }
        }

        graph.put(node, newMap)
    }
}

class Solution2:Sol {
    override fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val graph = hashMapOf<String, HashMap<String, Double>>()
        for(i in equations.indices){
            //equations[i][0] = values[i] * equations[i][1]
            graph.getOrPut(equations[i][0]){ hashMapOf() }.apply {
                put(equations[i][1], values[i])
                put(equations[i][0], 1.0)
            }

            graph.getOrPut(equations[i][1]){ hashMapOf() }.apply {
                put(equations[i][0], 1/values[i])
                put(equations[i][1], 1.0)
            }
        }

//        println("graph:$graph")

        val result = DoubleArray(queries.size)
        for((index,query) in queries.withIndex()){
//            println("query:$query")

            var subResult:Double = -1.0

            if(graph.get(query[0])!=null && graph.get(query[1])!=null) {
                if (query[0] == query[1])
                    subResult = 1.0
                else{
                    //BFS find target
                    val start = query[0]
                    val target = query[1]
                    val visited = HashSet<String>().apply { add(start) }
                    val queue = ArrayDeque<Pair<String, Double>>().apply { addLast(start to 1.0) }
                    while (queue.isNotEmpty()){
                        val pop:Pair<String, Double> = queue.removeFirst()
                        val n = pop.first
                        val multiplier = pop.second

                        if(n == target)
                            subResult = multiplier
                        visited.add(n)

                        for (next in graph.get(n)!!) {
                            if(visited.contains(next.key)) continue

                            queue.addLast(next.key to next.value * multiplier)
                        }
                    }
                }
            }
//            println("subresult:$subResult")
            result[index] = subResult
        }

        return result
    }
}