import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        val comparator = Comparator<Int> { o1, o2 ->
            val disO1 = Math.abs(x-o1)
            val disO2 = Math.abs(x-o2)
            if(disO1 == disO2){
                o2 - o1
            }else{
                disO2 - disO1
            }
        }

        //maxHeap with size k
        val heap = PriorityQueue(comparator).apply {
            for(el in arr){
                this.add(el)

                if(this.size>k)
                    this.remove()
            }
        }

        return heap.sorted()
    }
}

/*
constraint中保證一定會有答案
所以arr第一位要是比k大 那就是開頭前K位，反過來說最後一位比K小 那就是結尾那K位
答案的list(ans) 規定是照順序排，那list兩端跟K的距離一定是最長，可以用一個size K的queue
queue size不足K時無條件塞進，queue size等於k時，判斷peek這位 和進來的這位 跟K的距離誰大
如果peek這位距離大 就把這位remove，換現在這位push近來

距離的部份其實不需要用到abs，peek位(A)和進來這位(B)有三種情況，而其中A<B為已知
1.A B 都<=K，A B 誰小 誰距離K越大(A)
2.A B 都>=K，A B 誰大 誰距離K越大(B)
3.A<=K B>=K，比較K-A 和 B-K誰大誰距離越大
那試看看case1 2帶進case3是否也成立：
case1:A B 都<=K，K-A>=0 和 B-K<=0，的確成立「比較K-A 和 B-K誰大誰距離越大」
case1:A B 都>=K，K-A<=0 和 B-K>=0，的確成立「比較K-A 和 B-K誰大誰距離越大」

既然這樣三個判斷可以壓成一個
 */

class Solution2 {

    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        val queue = LinkedList<Int>()
        for(el in arr){
            if(queue.size<k)
                queue.addLast(el)
            else{
                if(el - x >= x-queue.first)
                    break
                else{
                    queue.removeFirst()
                    queue.addLast(el)
                }
            }
        }
        return queue
    }
}