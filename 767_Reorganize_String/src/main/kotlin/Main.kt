import java.util.*

class Solution {
    fun reorganizeString(s: String): String {
        if(s.length==1)
            return s

        val counting = IntArray(26).apply {
            s.forEach { this[it - 'a']++ }
        }

        val maxHeap = PriorityQueue<Int>{ t1: Int, t2:Int->
            if(counting[t1] == counting[t2])
                t1-t2
            else
                counting[t2] - counting[t1]
        }
        counting.forEachIndexed { index, i ->
            if(i>0) maxHeap.add(index)
        }

        val result = StringBuilder()
        while (maxHeap.size>=2){
            val el1 = maxHeap.remove()
            result.append('a' + el1)
            --counting[el1]

            val el2 = maxHeap.remove()
            result.append('a' + el2)
            --counting[el2]

            if(counting[el1]!=0)
                maxHeap.add(el1)
            if(counting[el2]!=0)
                maxHeap.add(el2)
        }

        if(maxHeap.size!=0){
            if(counting[maxHeap.peek()]>1) return ""

            val el = maxHeap.remove()
            result.append('a' + el)
        }
        return result.reverse().toString()
    }
}
