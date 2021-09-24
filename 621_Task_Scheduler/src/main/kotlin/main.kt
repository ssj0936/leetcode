import java.util.*

/*
621. Task Scheduler(Medium)

Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation:
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:
Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.

Example 3:
Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation:
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

Constraints:
1 <= task.length <= 10^4
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].
 */

interface Solu{
    fun solu(tasks: CharArray, n: Int): Int
}

class Solution :Solu{

    /*
    基本想法是對所有task計算出現次數 從大到小排列
    每一回合 從出現頻率最大的往下數n+1個 各減1 並同時計算步數(長度)
    直到把所有頻率都減完 return步數

   卡關在如何每次都拿最大 -> priority queue
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        if(n==0) return tasks.size

        val map: MutableMap<Char, Int> = mutableMapOf()
        for (task in tasks){
            map[task] = map.getOrDefault(task,0)+1
        }

        val priorityQueue = PriorityQueue<Int>(compareBy { -it }).apply {
            map.forEach { this.offer(it.value)}
        }

        var step = 0
        val stack = Stack<Int>()
        while (priorityQueue.isNotEmpty()){
            var tmpStep = 0
            for(i in 1 .. n+1){
                if(priorityQueue.isEmpty()) break
                stack.push(priorityQueue.poll()-1)
                ++tmpStep
            }

            while (stack.isNotEmpty()){
                val top = stack.pop()
                if(top>0) priorityQueue.offer(top)
            }

            step += if(priorityQueue.isNotEmpty()) n+1 else tmpStep
        }
        return step
    }

    override fun solu(tasks: CharArray, n: Int): Int  = leastInterval(tasks, n)
}
fun main(args: Array<String>) {
    println("Hello World!")
}