import java.util.PriorityQueue

class SolutionOriginal {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val table = Array(n){HashSet<Int>()}

        for(road in roads){
            table[road[0]].add(road[1])
            table[road[1]].add(road[0])
        }

        var max = 0
        for(i in 0 until n-1){
            for(j in i+1 until n){
                max = maxOf(max, (table[i].size + table[j].size + (if(table[i].contains(j) || table[j].contains(i)) -1 else 0)))
            }
        }
        return max
    }
}

class Solution {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val table = Array(n){HashSet<Int>()}
        val inDegrees = IntArray(n)

        for(road in roads){
            table[road[0]].add(road[1])
            table[road[1]].add(road[0])

            ++inDegrees[road[0]]
            ++inDegrees[road[1]]
        }

        val minHeap = PriorityQueue<Int>()
        for(indeg in inDegrees){
            if(minHeap.size<2)
                minHeap.add(indeg)
            else if(indeg > minHeap.peek()){
                minHeap.remove()
                minHeap.add(indeg)
            }
        }
        val secondMaxDegree = minHeap.remove()?:0
        val maxDegree = minHeap.remove()?:0
        var maxDegCount = 0
        var secondMaxDegCount = 0
        for(inDeg in inDegrees){
            if(inDeg == maxDegree) ++maxDegCount
            else if(inDeg == secondMaxDegree) ++secondMaxDegCount
        }
        println("maxDegree:$maxDegree, secondMaxDegree:$secondMaxDegree")
        println("maxDegCount:$maxDegCount, secondMaxDegCount:$secondMaxDegCount")


        if(maxDegCount>=2){
            var counter = maxDegCount * (maxDegCount-1) / 2
            for(road in roads){
                if(inDegrees[road[0]] == maxDegree && inDegrees[road[1]] == maxDegree) {
                    --counter
                }
            }
            return maxDegree*2 + if(counter==0) -1 else 0
        }else{
            var counter = secondMaxDegCount

            for(road in roads){
                if(inDegrees[road[0]] == maxDegree && inDegrees[road[1]] == secondMaxDegree){
                    --counter
                }else if(inDegrees[road[1]] == maxDegree && inDegrees[road[0]] == secondMaxDegree) {
                    --counter
                }
            }

            return maxDegree + secondMaxDegree + if(counter==0) -1 else 0
        }
    }
}