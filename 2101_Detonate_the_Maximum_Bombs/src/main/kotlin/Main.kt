import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val n = bombs.size
        val isConnected = Array<HashSet<Int>>(n){hashSetOf()}

        //n!
        for(i in 0 until n){
            val thisBomb = bombs[i]
            for(j in i+1 until n){
                val thatBomb = bombs[j]

                val thisBombX = thisBomb[0].toDouble()
                val thisBombY = thisBomb[1].toDouble()
                val thisBombR = thisBomb[2].toDouble()

                val thatBombX = thatBomb[0].toDouble()
                val thatBombY = thatBomb[1].toDouble()
                val thatBombR = thatBomb[2].toDouble()

                ((thisBombX-thatBombX)*(thisBombX-thatBombX) + (thisBombY-thatBombY)*(thisBombY-thatBombY)).run{
                    if((thisBombR*thisBombR) >= this)
                        isConnected[i].add(j)

                    if((thatBombR*thatBombR)>= this)
                        isConnected[j].add(i)
                }
            }
        }

//        println(isConnected.contentToString())

        //we need to get total count but level count,
        //so DFS is more suitable on this case than BFS
        val score = IntArray(n)
        var max = 1
        for(bomb in 0 .. n-1){
//            println("================")
            dfs(bomb, isConnected, BooleanArray(n), score, bomb)
            if(score[bomb]>max)
                max = score[bomb]
        }
        return max
    }

    private fun dfs(id:Int, graph: Array<HashSet<Int>>, visited:BooleanArray, score:IntArray, startIndex:Int){
        if(visited[id])
            return


        ++score[startIndex]
        visited[id] = true
//        println("id:$id, score[$startIndex]:${score[startIndex]}, graph[$id]:${graph[id]}")

        for(bomb in graph[id]){
            dfs(bomb, graph, visited, score, startIndex)
        }
    }
}

class Solution2 {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val n = bombs.size
        val table = Array<HashSet<Int>>(n){hashSetOf()}

        //n^2
        for(i in 0 until n){
            val thisBomb = bombs[i]
            for(j in 0 until n){
                if(i == j) continue
                val thatBomb = bombs[j]

                //radium is bigger than dist between 2 point
                if(inRange(thisBomb, thatBomb)){
                    table[i].add(j)
                }
            }
        }
//        println(table.contentToString())

        var max = 1
        val childTable = Array<HashSet<Int>>(n){hashSetOf()}
        for((i,entry) in table.withIndex()){
            if(entry.isEmpty()) continue

            val set = childTable[i]
            val queue = LinkedList<Int>().apply { add(i) }
            while (queue.isNotEmpty()){
                val pop = queue.poll()
                set.add(pop)

                for(j in table[pop]){
                    //if done already, just poll in
                    if(childTable[j].isNotEmpty()){
                        set.addAll(childTable[j])
                    }else if(!set.contains(j))
                        queue.offer(j)
                }
            }
//            childTable[i] = set
            if(set.size>max)
                max = set.size
        }
//        println(childTable.contentToString())
        return max
    }


    private fun inRange(thisBomb:IntArray, thatBomb:IntArray):Boolean{
        val thisBombX = thisBomb[0].toDouble()
        val thisBombY = thisBomb[1].toDouble()
        val thisBombR = thisBomb[2].toDouble()

        val thatBombX = thatBomb[0].toDouble()
        val thatBombY = thatBomb[1].toDouble()

        return (thisBombR*thisBombR) >= ((thisBombX-thatBombX)*(thisBombX-thatBombX) + (thisBombY-thatBombY)*(thisBombY-thatBombY))
    }
}