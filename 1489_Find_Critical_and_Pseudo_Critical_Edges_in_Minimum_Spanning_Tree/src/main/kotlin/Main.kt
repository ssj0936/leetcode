class Solution {
    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {
        fun getMSTWeight(except:Int = -1, guarantee:Int = -1):Int{
            val parent = IntArray(n){it}
            fun findRoot(node:Int):Int{
                if(parent[node] == node)
                    return node

                parent[node] = findRoot(parent[node])
                return parent[node]
            }

            fun union(node1:Int, node2: Int){
                val root1 = findRoot(node1)
                val root2 = findRoot(node2)
                parent[root1] = root2
            }

            val edgesIndex = edges.indices.sortedBy { edges[it][2] }

            var weight = 0
            var count = 0

            if(guarantee!=-1){
                val endPoint1 = edges[guarantee][0]
                val endPoint2 = edges[guarantee][1]
                val w = edges[guarantee][2]
                if(findRoot(endPoint1)!= findRoot(endPoint2)){
                    weight += w
                    ++count
                    union(endPoint1, endPoint2)
                }
            }

            for(edgeIndex in edgesIndex){
                if(except == edgeIndex) continue

                val endPoint1 = edges[edgeIndex][0]
                val endPoint2 = edges[edgeIndex][1]
                val w = edges[edgeIndex][2]
                if(findRoot(endPoint1)!= findRoot(endPoint2)){
                    weight += w
                    ++count
                    union(endPoint1, endPoint2)
                    if(count==n-1)
                        break
                }
            }
            return weight
        }


        val MSTWeight = getMSTWeight()
        val criticalEdges = mutableListOf<Int>()
        val nonCriticalEdges = mutableListOf<Int>()

        for(i in edges.indices){
            if(MSTWeight != getMSTWeight(except = i))
                criticalEdges.add(i)
            else {
                if (MSTWeight == getMSTWeight(guarantee = i)) {
                    nonCriticalEdges.add(i)
                }
            }
        }

        return listOf(criticalEdges, nonCriticalEdges.toList())
    }
}