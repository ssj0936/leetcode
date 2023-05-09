fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val result = IntArray(numCourses){it}
        val connection = mutableMapOf<Int, MutableSet<Int>>()
        prerequisites.forEach {
            val c = it[0]
            val preClass = it[1]
            //key = 課程, value = 先修課
            connection.getOrPut(c){mutableSetOf()}.add(preClass)
            result[c] = -1
        }

        val queue = mutableListOf<Int>()
        //find in-degree 0 point
        result.forEach {
            if(it != -1)
                queue.add(it)
        }

        if(queue.size==0)
            return intArrayOf()

        var queuePtr = 0
        while (queuePtr < queue.size){
            val node = queue[queuePtr]
            result[queuePtr] = node
            val removed = mutableListOf<Int>()
            connection.forEach {
                it.value.remove(node)
                if(it.value.size == 0) {
                    removed.add(it.key)
                }
            }

            removed.forEach {
                connection.remove(it)
                queue.add(it)
            }
            ++queuePtr
        }
        if(queue.size!=numCourses)
            return intArrayOf()

        return result
    }
}

class Solution2 {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val result = IntArray(numCourses){it}

//        if(prerequisites.isEmpty())
//            return result

        val graph = Array(numCourses){ mutableListOf<Int>() }
        val inDegree = IntArray(numCourses){0}
        prerequisites.forEach {
            val c = it[0]
            val preClass = it[1]
            //key = 課程, value = 先修課
            graph[preClass].add(c)
            ++inDegree[c]
        }
//        println("inDegree:${inDegree.contentToString()}")
//        println("graph:${graph.contentToString()}")

        val queue = ArrayDeque<Int>()
        //find in-degree 0 point
        inDegree.forEachIndexed { index, it ->
            if(it == 0)
                queue.addLast(index)
        }

        var ptr = 0
        while (queue.isNotEmpty()){

            val node = queue.removeFirst()
//            println("node:$node")
            result[ptr++] = node

            for(nextPoint in graph[node]){
                if(--inDegree[nextPoint] == 0)
                    queue.addLast(nextPoint)
            }
        }
//        println(ptr)

        if(ptr != numCourses)
            return intArrayOf()

        return result
    }
}