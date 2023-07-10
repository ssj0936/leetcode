class Solution {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortWith (object :Comparator<IntArray>{
                override fun compare(o1: IntArray, o2: IntArray): Int {
                    return o1[1]-o2[1]
                }
        })

        var removeCount = 0
        var prevI = 0
        for(i in 1 .. intervals.lastIndex){
            if(intervals[i][0] < intervals[prevI][1]){
                ++removeCount
            }else
                prevI = i
        }

        return removeCount
    }
}

class SolutionOri {
    var max = Int.MIN_VALUE
    var memo = hashMapOf<IntArray,Int>()
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {

        intervals.sortWith(object :Comparator<IntArray>{
            override fun compare(o1: IntArray, o2: IntArray): Int {
                //2. sortStart
                //same start, shorter one on front
                if(o1[0]==o2[0]){
                    return o1[1] - o2[1]
                }else//smaller start on front
                    return o1[0] - o2[0]
            }
        })

        var prevStart = Int.MIN_VALUE
        for(i in intervals.indices){
            if(prevStart==intervals[i][0]) continue
            prevStart = intervals[i][0]

            val value = helper(intervals, intervals.size, i)
            max = maxOf(max, value)
        }

        return intervals.size - max
    }

    //取用intervals[startI]的話 最多能拿幾的interval
    private fun helper(intervals: Array<IntArray>, size:Int, startI:Int):Int{
        if(startI>=size)
            return 0
        if(memo.containsKey(intervals[startI]))
            return memo.get(intervals[startI])!!

        var m = 1
        var prevStart = -1
        val searchFrom = intervals.bs(intervals[startI][1], startI+1)
        for(i in searchFrom until size){
            if(prevStart==intervals[i][0]) continue
            prevStart = intervals[i][0]

            if(intervals[i][0] >= intervals[startI][1]){

                var intervalCount =if(memo.containsKey(intervals[i])) {
                    memo.get(intervals[i])!! +1
                }else {
                    helper(intervals, size, i) + 1
                }

                m = maxOf(m, intervalCount)
            }
        }
        memo.put(intervals[startI], m)

        return m
    }

    private fun Array<IntArray>.bs(target:Int, start:Int = 0, end:Int = this.lastIndex):Int{
        var head = start
        var tail = end

        while (head<=tail){
            val mid = (head+tail)/2
            if(this[mid][0] == target){
                tail = mid -1
            }else if(this[mid][0] < target){
                head = mid + 1
            }else{
                tail = mid -1
            }
        }

        return head
    }
}