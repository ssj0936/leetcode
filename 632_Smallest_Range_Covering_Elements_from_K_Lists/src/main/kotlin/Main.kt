import java.util.PriorityQueue

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun smallestRange(nums: List<List<Int>>): IntArray {
        var minRangeFrom:Int = Int.MAX_VALUE
        var minRangeTo:Int = Int.MIN_VALUE

        var prevRangeFrom:Int = Int.MAX_VALUE
        var prevRangeTo:Int = Int.MIN_VALUE

        //pair<value, index>
        val minHeap = PriorityQueue<HeapClass>(compareBy { it.value }).apply {
            for(i in nums.indices){
                this.add(HeapClass(nums[i][0], i, 0))
                prevRangeFrom = minOf(prevRangeFrom, nums[i][0])
                prevRangeTo = maxOf(prevRangeTo, nums[i][0])
            }
        }

        //init
        minRangeFrom = prevRangeFrom
        minRangeTo = prevRangeTo

        while (true){
            val pop = minHeap.remove()
            val iBelongTo = pop.indexI
            val jBelongTo = pop.indexJ

            if(jBelongTo+1 > nums[iBelongTo].lastIndex)
                break

            val newInsert = nums[iBelongTo][jBelongTo+1].also {
                minHeap.add(HeapClass(it, iBelongTo, jBelongTo+1))
            }

            val newRangeFrom = minHeap.peek().value
            val newRangeTo = maxOf(newInsert,prevRangeTo)

            if((newRangeTo - newRangeFrom) < (minRangeTo - minRangeFrom)
                || (((newRangeTo - newRangeFrom) == (minRangeTo - minRangeFrom)) && newRangeFrom < minRangeFrom)){
                minRangeFrom = newRangeFrom
                minRangeTo = newRangeTo
            }

            prevRangeFrom = newRangeFrom
            prevRangeTo = newRangeTo

        }

        return intArrayOf(minRangeFrom, minRangeTo)
    }

    data class HeapClass(
        val value:Int,
        val indexI:Int,
        val indexJ:Int
    )
}