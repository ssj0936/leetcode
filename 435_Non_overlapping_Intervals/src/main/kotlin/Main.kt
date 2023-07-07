class Solution {
    var max = Int.MIN_VALUE
    var memo = hashMapOf<IntArray,Int>()
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {

        intervals.sortWith(object :Comparator<IntArray>{
            override fun compare(o1: IntArray, o2: IntArray): Int {
//                //1. sortEnd
//                //same end, shorter one on back
//                if(o1[1]==o2[1]){
//                    return o2[0]-o1[0]
//                }else//bigger emd on back
//                    return o1[1]-o2[0]

                //2. sortStart
                //same start, shorter one on front
                if(o1[0]==o2[0]){
                    return o1[1] - o2[1]
                }else//smaller start on front
                    return o1[0] - o2[0]
            }
        })

//        println(intervals.contentDeepToString())
        var prevStart = Int.MIN_VALUE
        for(i in intervals.indices){
            if(prevStart==intervals[i][0]) continue
            prevStart = intervals[i][0]

            val value = helper(intervals, intervals.size, i)
            max = maxOf(max, value)
        }

//        helper(intervals, intervals.size, Int.MIN_VALUE, 0)
        return intervals.size - max
    }

    //取用intervals[startI]的話 最多能拿幾的interval
    private fun helper(intervals: Array<IntArray>, size:Int, startI:Int):Int{
//        println("helper(startI:$startI : (${intervals[startI].contentToString()}))")
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
//                println("testing intervals[$i]:${intervals[i].contentToString()}")

                var intervalCount =if(memo.containsKey(intervals[i])) {
//                    println("memo:${memo.get(intervals[i])!!}")
                    memo.get(intervals[i])!! +1
                }else {
//                    println("deeper")
                    helper(intervals, size, i) + 1
                }

                m = maxOf(m, intervalCount)
            }
        }

//        if(startI!=0)
        memo.put(intervals[startI], m)
//        println("${intervals[startI].contentToString()}:$m")
//        max = maxOf(max, m)

        return m
    }

    private fun Array<IntArray>.bs(target:Int, start:Int = 0, end:Int = this.lastIndex):Int{
//        println("target:$target")
        var head = start
        var tail = end

        while (head<=tail){
            val mid = (head+tail)/2
//            println("mid:$mid")

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