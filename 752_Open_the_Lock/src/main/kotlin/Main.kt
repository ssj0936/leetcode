import java.util.*

/*
752. Open the Lock(Medium)
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
The lock initially starts at '0000', a string representing the state of the 4 wheels.
You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.

Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1

Constraints:

1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.

We can think of this problem as a shortest path problem on a graph:
there are `10000` nodes (strings `'0000'` to `'9999'`),
and there is an edge between two nodes if they differ in one digit,
that digit differs by 1 (wrapping around, so `'0'` and `'9'` differ by 1),
and if *both* nodes are not in `deadends`.
 */

class Solution {

    //提示講了 說要以圖學的方式去切入
    //目前想用BFS的方式，每一個點發散出八個子點
    //碰到deadlock就放棄這路徑
    //碰到走過的也放棄
    //直到整張圖走完為止

    /*
    檢討：
    花的時間很多，花在：
    1.plusOne/minusOne的assign
    2.level的計算


     */
    val routed = mutableSetOf("0000")
    val deadendsSet = mutableSetOf<String>()

    fun openLock(deadends: Array<String>, target: String): Int {
        if (target == "0000") return 0
        if (deadends.contains("0000")) return -1
        deadends.forEach { deadendsSet.add(it) }

        //這裡的queue採用Pair方式做紀錄，用來把level一起帶進遞迴裡，方便計算
        //無腦level+1 會超級暴增
        val queue = LinkedList<Pair<Int,String>>().apply { add(Pair(0,"0000")) }
        return BFS(queue, target)
    }

    private fun BFS(queue:Queue<Pair<Int,String>>, target:String):Int{
        if(queue.isEmpty()) return -1

        val node = queue.poll()
        val newLevel = node.first+1

        val arr = node.second.toCharArray()
        for(i in arr.indices){
            /*
            注意：
            1. toString會印出記憶體位置，charArray轉字串應該要String(charArray)
            val pl:String = arr.also {
                if(it[i]+1 >'9') it[i] ='0' else ++it[i]
            }.toString()

            2.
            val pl:String = String(arr.also {
                if(it[i]+1 >'9') it[i] ='0' else ++it[i]
            })
            這樣會直接改到本體，應該要clone出來
             */

            val plusOne:String = arr.clone().let {
                if(it[i]+1 >'9') it[i] ='0' else ++it[i]
                String(it)
            }

            if(!routed.contains(plusOne) && !deadendsSet.contains(plusOne) && target!=plusOne){
                routed.add(plusOne)
                queue.add(Pair(newLevel,plusOne))

            }else if(target==plusOne){
                return newLevel
            }

            val minusOne:String = arr.clone().let {
                if(it[i]-1 < '0') it[i] ='9' else --it[i]
                String(it)
            }
            if(!routed.contains(minusOne) && !deadendsSet.contains(minusOne) && target!=minusOne){
                routed.add(minusOne)
                queue.add(Pair(newLevel,minusOne))

            }else if(target==minusOne){
                return newLevel
            }
        }
        return BFS(queue, target)
    }
}
fun main(args: Array<String>) {
    val deadends = arrayOf("0201","0101","0102","1212","2002")
    val target = "0202"
    val result = Solution().openLock(deadends,target)
    println(result)
}