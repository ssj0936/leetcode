/*
310. Minimum Height Trees
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:

Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int>
}

class Solution:Sol {

    override fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if(n==1)
            return listOf(n-1)

        val map = HashMap<Int,MutableList<Int>>()
        for(edge in edges){
            map.put(edge[0], map.getOrDefault(edge[0], mutableListOf()).apply { add(edge[1]) })
            map.put(edge[1], map.getOrDefault(edge[1], mutableListOf()).apply { add(edge[0]) })
        }
        var minHeight = Int.MAX_VALUE
        val result = HashMap<Int,MutableList<Int>>()

        for(node in 0 until n){
            val nodeHeight = getHeight(node, map, -1, mutableSetOf(node))
            result.put(nodeHeight, result.getOrDefault(nodeHeight, mutableListOf()).apply { add(node) })
            minHeight = Math.min(minHeight, nodeHeight)
        }

        return result.get(minHeight)!!.toList()
    }

    private fun getHeight(node:Int, map:HashMap<Int,MutableList<Int>>, level:Int, passedSet:MutableSet<Int>):Int{
        passedSet.add(node)
        if(passedSet.containsAll(map.get(node)!!)) {
            return level + 1
        }else {
            var height = Int.MIN_VALUE
            for (child in map.get(node)!!) {
                if (passedSet.contains(child)) continue
                height = Math.max(height, getHeight(child, map, level + 1, passedSet))
            }
            return height
        }
    }
}

class Solution2:Sol {

    override fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if(n==1)
            return listOf(n-1)

        val heightTable = HashMap<String,Int>()
        for(i in 0 until n) heightTable.put("$i,$i",0)

        val map = HashMap<Int,MutableList<Int>>()
        for(edge in edges){
            map.put(edge[0], map.getOrDefault(edge[0], mutableListOf()).apply { add(edge[1]) })
            map.put(edge[1], map.getOrDefault(edge[1], mutableListOf()).apply { add(edge[0]) })
        }
        var minHeight = Int.MAX_VALUE
        val result = HashMap<Int,MutableList<Int>>()

        for(node in 0 until n){
//            println("-----$node-----")
            val nodeHeight = getHeight(node, map, -1, mutableSetOf(node),heightTable)
            result.put(nodeHeight, result.getOrDefault(nodeHeight, mutableListOf()).apply { add(node) })
            minHeight = Math.min(minHeight, nodeHeight)
//            println("------------")
        }

        return result.get(minHeight)!!.toList()
    }

    private fun getHeight(node:Int, map:HashMap<Int,MutableList<Int>>, level:Int, passedSet:MutableSet<Int>, table:HashMap<String,Int>):Int{
//        println("node:$node, passedSet:$passedSet")

        passedSet.add(node)
        if(passedSet.containsAll(map.get(node)!!)) {
            return level + 1
        }else {
            var height = Int.MIN_VALUE
            for (child in map.get(node)!!) {
                if (passedSet.contains(child)) continue

                if (table.containsKey("$node,$child")){
//                    println("done: table[$node][$child] = ${table[node][child]}")
                    height = Math.max(height, table.get("$node,$child")!!+(level+1))
                }else {
                    val subHeight = getHeight(child, map, level + 1, passedSet, table)
                    height = Math.max(height, subHeight)
//                    println("record: table[$node][$child] = ${subHeight-(level+1)}")
                    table.put("$node,$child",subHeight-(level+1))
                }
            }
//            println("height:$height")
            return height
        }
    }
}

class Solution3:Sol {

    override fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1)
            return listOf(n - 1)

        val map = Array<MutableList<Int>>(n){ mutableListOf()}
        for(edge in edges){
            map[edge[0]].add(edge[1])
            map[edge[1]].add(edge[0])
        }

        while (true){
            val set = mutableSetOf<Int>()
            for(node in map){

            }

        }

    }
}