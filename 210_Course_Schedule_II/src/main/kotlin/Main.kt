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
                val success = it.value.remove(node)
                if(success && it.value.size == 0) {
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