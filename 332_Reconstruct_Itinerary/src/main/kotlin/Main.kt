import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

const val START = "JFK"
class Solution {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val n = tickets.size
        val graph = HashMap<String, MutableList<String>>().apply {
            tickets.forEach{
                val from = it[0]
                val to = it[1]
                this.getOrPut(from){ mutableListOf()}.add(to)
            }

            this.forEach { (_, value) ->
                value.sort()
            }
        }

        val visited = HashSet<String>()
        val stack = LinkedList<String>().apply {push(START)}
        helper(graph,n+1, stack, visited)
        return stack.toList().reversed()
    }

    private fun helper(graph:HashMap<String, MutableList<String>>, n:Int, stack:LinkedList<String>, visited:HashSet<String>){
        if(stack.size == n) return

        val thisStop = stack.peek()
        if( graph.get(thisStop) == null) return
        for((i,nextAvailableStop) in graph.get(thisStop)!!.withIndex()){
            if(!visited.contains("$thisStop$nextAvailableStop$i")){
                visited.add("$thisStop$nextAvailableStop$i")
                stack.push(nextAvailableStop)

                helper(graph, n, stack, visited)
                if(stack.size == n) return

                visited.remove("$thisStop$nextAvailableStop$i")
                stack.pop()
            }
        }
    }
}