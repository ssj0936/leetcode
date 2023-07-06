import java.util.LinkedList

class Solution {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val graph = Array<HashMap<Int,Int>>(n){ hashMapOf() }
        val price = IntArray(n){-1}.apply { this[src]=0 }
        val visited = BooleanArray(n){false}

        flights.forEach {
            val from = it[0]
            val to = it[1]
            val cost = it[2]
            graph[from].put(to,cost)
        }

        val bfsQueue = LinkedList<Int>().apply { offer(src) }
//        val counter =
        var localK = k
        while (bfsQueue.isNotEmpty() && localK>=0) {
            val size = bfsQueue.size
            val tmp = mutableMapOf<Int,Int>()
            repeat(size){
                val from = bfsQueue.poll()
                // println("from:$from")
                visited[from]=true
                graph[from].forEach {
                    val to = it.key
                    val cost = it.value
                    val costFromThisPoint = price[from] + cost

                    if(!visited[to] || (visited[to] && price[to] > costFromThisPoint)){
                        tmp.put(to, minOf(costFromThisPoint,tmp.getOrDefault(to,Int.MAX_VALUE)))
                        // println("tmp put($to, ${tmp.get(to)})")
//                        price[to] = costFromThisPoint
                        // visited[to] = true
                        bfsQueue.offer(to)
                    }
                }
            }

            tmp.forEach {(to, cost)->
                price[to] = cost
                visited[to] = true
            }
            // println(price.contentToString())
            --localK
        }

        return price[dst]
    }
}