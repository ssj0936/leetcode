import java.util.*

class SolutionOri {
    fun candy(ratings: IntArray): Int {
        val result = IntArray(ratings.size)
        val minHeap = PriorityQueue<Int>(compareBy { result[it] })
        for(i in ratings.indices){
            val prev = i-1
            val next = i+1

            if((prev<0 || (prev>=0 && ratings[prev]>ratings[i])) &&
                (next>ratings.lastIndex || (next<=ratings.lastIndex && ratings[next]>ratings[i]))) {
                minHeap.add(i)
            }
        }

        while (minHeap.isNotEmpty()){
            val size = minHeap.size
            val set = hashSetOf<Int>()
            repeat(size){
                val pop = minHeap.remove()
                val prev = pop-1
                val next = pop+1

                var value = 1
                if(prev>=0){
                    if(result[prev]==0)
                        set.add(prev)

                    if(ratings[pop] > ratings[prev])
                        value = maxOf(value, result[prev]+1)
                }

                if(next<=ratings.lastIndex){
                    if(result[next]==0)
                        set.add(next)

                    if(ratings[pop] > ratings[next])
                        value = maxOf(value, result[next]+1)
                }
                result[pop] = value
            }

            set.forEach {
                minHeap.add(it)
            }
        }

        return result.sum()
    }
}

class Solution {
    fun candy(ratings: IntArray): Int {
        val result1 = IntArray(ratings.size){1}
        val result2 = IntArray(ratings.size){1}
        for(i in 1 .. ratings.lastIndex){
            if(ratings[i]>ratings[i-1])
                result1[i] = result1[i-1]+1
        }

        for(i in ratings.lastIndex-1 downTo 0){
            if(ratings[i]>ratings[i+1])
                result2[i] = result2[i+1]+1
        }
        val result = IntArray(ratings.size).apply {
            for(i in result1.indices){
                this[i] = maxOf(result1[i], result2[i])
            }
        }

        return result.sum()
    }
}