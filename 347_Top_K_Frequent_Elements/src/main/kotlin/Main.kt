import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val countTable = HashMap<Int, Int>()
        for(num in nums){
            countTable[num] = countTable.getOrDefault(num,0)+1
        }

        val heap = PriorityQueue<Int>(compareBy { countTable.get(it)!!*-1 }).apply {

            countTable.forEach { this.add(it.key) }
        }

        val result = IntArray(k).apply {
            for(i in 0 until k)
                this[i] = heap.remove()
        }
        return result
    }
}

class Solution2 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val countTable = HashMap<Int, Int>()
        for(num in nums){
            countTable[num] = countTable.getOrDefault(num,0)+1
        }

        val heap = PriorityQueue<Int>(compareBy { countTable.get(it)!!}).apply {
            for(entry in countTable){
                this.add(entry.key)

                if(this.size>k)
                    this.remove()
            }
        }

        val result = IntArray(k).apply {
            var counter = 0
            heap.forEach {
                this[counter++] = it
            }
        }
        return result
    }
}