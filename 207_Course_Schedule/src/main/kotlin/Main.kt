/*
207. Course Schedule
Medium
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    val DEFAULT = 0
    val VISITING = 1
    val VISITED = 2

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        //先定義圖怎麼畫
        //課程node -> 先修課node

        //先把prerequisites用Array<Pair<Int, List<Int>>>做成圖
        //list是放該點可以連到那些node
        val map = Array<MutableList<Int>>(numCourses) {
            mutableListOf()
        }

        //1 -> visiting, 2 -> visited
        val topologicalStatus = Array(numCourses){DEFAULT}

        for(pair in prerequisites){
            map[pair[0]].add(pair[1])
        }

        //每個點走一遍
        for(i in 0 until numCourses){
            if(topologicalStatus[i]==VISITED) continue
            if(hasCycle(i, map, topologicalStatus)) return false
        }

        return true
    }

    //打上VISITED代表已經檢查過 沒圈，所以走到這點直接return false
    //打上VISITING代表這點正在這一輪的過程 ，所以走到這點直接return true
    //再來 就是先做個VISITING記號，去recursive檢查這個點的每個下線，都檢查完就作VISITED記號，return 沒圈
    private fun hasCycle(courseID:Int, map:Array<MutableList<Int>>,topologicalStatus:Array<Int>):Boolean{
        if(topologicalStatus[courseID]==VISITED) return false
        if(topologicalStatus[courseID]==VISITING) return true

        topologicalStatus[courseID] = VISITING
        for(node in map[courseID]){
            if(hasCycle(node,map,topologicalStatus)) return true
        }
        topologicalStatus[courseID] = VISITED
        return false
    }
}