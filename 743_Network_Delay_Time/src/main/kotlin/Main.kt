class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {

        //dist[i][j] map to dist of node i to node j
        val dist = hashMapOf<Pair<Int, Int>, Int>().apply {
            times.forEach { this[it[0] to it[1]] = it[2] }
        }

        val distFromSources = IntArray(n+1){Int.MAX_VALUE}.apply {
            this[k] = 0
        }

        val map = Array(n+1){ mutableListOf<Int>() }.apply {
            for(time in times){
                this[time[0]].add(time[1])
            }
        }
        
        val visited = hashSetOf<Int>()
        repeat(n-1){
            val minNode = distFromSources.withIndex().minByOrNull { (i,v) -> if(visited.contains(i)) Int.MAX_VALUE else v }?.index!!

            if(minNode == 0) return -1

            visited.add(minNode)
            map[minNode].filter { !visited.contains(it) }.forEach {node ->
                distFromSources[node] = minOf(distFromSources[node], distFromSources[minNode] + dist[minNode to node]!!)
            }
        }

        val maxTime = distFromSources.slice(1..n).maxOrNull()!!

        return if(maxTime== Int.MAX_VALUE) -1 else maxTime
    }
}

class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val dist = IntArray(n+1){ Int.MAX_VALUE}.apply { this[k]=0 }
        repeat(n-1) {
            var hasAnyUpdate = false
            for (edge in times) {
                val start = edge[0]
                val end = edge[1]
                val weight = edge[2]

                if(dist[start]!= Int.MAX_VALUE && dist[start] + weight < dist[end]){
                    dist[end] = dist[start] + weight
                    hasAnyUpdate = true
                }
            }

            if(!hasAnyUpdate)
                return@repeat
        }

        var maxDelay = Int.MIN_VALUE
        for(i in 1 .. dist.lastIndex){
            maxDelay = maxOf(maxDelay, dist[i])
        }

        return if (maxDelay== Int.MAX_VALUE) -1 else maxDelay
    }
}