import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val depth = IntArray(graph.size) {-1}
        val queue = ArrayDeque<Int>()

        for (i in graph.indices) {
            if(depth[i]!=-1) continue

            queue.addLast(i)
            var deep = 0
            while (queue.isNotEmpty()){
                val size = queue.size
                repeat(size){
                    val pop = queue.removeFirst()
                    depth[pop] = deep
                    for(linkedNode in graph[pop]){
                        if(depth[linkedNode] == -1){
                            queue.addLast(linkedNode)
                        }else{
                            if((depth[pop]-depth[linkedNode])%2==0){
                                return false
                            }
                        }
                    }
                }
                ++deep
            }
        }
        return true
    }
}