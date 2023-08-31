import java.util.*

class SolutionQQ {
    fun minTaps(n: Int, ranges: IntArray): Int {
        val stack = LinkedList<Pair<Int, Int>>()

        for((i,range) in ranges.withIndex()){
            if(range == 0) continue

            val thisRange = Pair(maxOf(0,i-range), minOf(n, i+range))
            if(stack.isEmpty())
                stack.push(thisRange)
            else if(stack.peek().first<=thisRange.first
                && stack.peek().second>=thisRange.second) {
                continue
            }else{
                //fully overlapped remove
                while (stack.isNotEmpty()
                    && stack.peek().first>=thisRange.first
                    && stack.peek().second<=thisRange.second)
                    stack.pop()

                for(certainRange in stack.reversed()){
                    if(certainRange.second>=thisRange.first){
                        while (stack.isNotEmpty() && stack.peek()!=certainRange)
                            stack.pop()
                        break
                    }
                }
                stack.push(thisRange)
            }
//            println("$i : ${stack.reversed()}")
        }

        for(rangeI in 1 .. stack.lastIndex){
            if(stack[rangeI].second < stack[rangeI-1].first) return -1
        }

        return if(stack.isEmpty() || stack.peek().second<n) -1 else stack.size
    }
}

class Solution {
    fun minTaps(n: Int, ranges: IntArray): Int {
        val intervals = mutableListOf<Pair<Int, Int>>().apply {
            ranges.forEachIndexed { i, range ->  this.add(maxOf(0,i-range) to minOf(n, i+range))}
        }

        intervals.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

        var pointer = 0
        val record = mutableListOf<Pair<Int, Int>>().apply { add(intervals[pointer]) }
        while (pointer<intervals.size){
            var farthestI = pointer
            for(i in pointer+1 until intervals.size){
                if(intervals[i].first > intervals[pointer].second) break

                if(intervals[i].second > intervals[farthestI].second){
                    farthestI = i
                }
            }

            if(pointer == farthestI)
                break

            record.add(intervals[farthestI])
            pointer = farthestI
        }
        return if(record.last().second<n) -1 else record.size
    }
}