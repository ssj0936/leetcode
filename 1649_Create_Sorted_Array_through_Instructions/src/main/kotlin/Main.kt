class SolutionTLE {
    data class Node(
        val value:Int,
        var left:Node? = null,
        var right:Node? = null,
        var leftChildCount:Int = 0,
        var selfCount:Int = 1
    )
    val mod = 1000000000 + 7
    fun createSortedArray(instructions: IntArray): Int {
        var result = 0
        var root:Node? = null
        for((i,v) in instructions.withIndex()){
            if(root == null){
                root = Node(v)
            }else {
                var leftCount = 0
                var pointer: Node? = root
                var parentPointer: Node? = null
                while (pointer != null) {
                    if(pointer.value == v) {
                        ++pointer.selfCount
                        leftCount += pointer.leftChildCount
                        break
                    }else if(pointer.value < v){ // 往右接
                        parentPointer = pointer
                        pointer = pointer.right

                        leftCount += parentPointer.selfCount + parentPointer.leftChildCount
                    }else{
                        parentPointer = pointer
                        pointer = pointer.left

                        ++parentPointer.leftChildCount
                    }
                }

                if(pointer==null){
                    pointer = Node(v)
                    if(parentPointer!!.value < v){
                        parentPointer.right = pointer
                    }else{
                        parentPointer.left = pointer
                    }
                }

                val rightCount = (i+1) - pointer.selfCount - leftCount
                val subResult = minOf(rightCount, leftCount)

                result = (result + subResult)%mod
            }
        }

        return result
    }
}

class Solution {
    val mod = 1000000000 + 7
    var result = 0
    var LEFT = 0
    var RIGHT = 1
    fun createSortedArray(instructions: IntArray): Int {
        var sorted = instructions.clone()
        var count = Array(instructions.size){IntArray(2)}
        helper(instructions, sorted, 0, instructions.lastIndex,count)

        count.forEach { result = (result + minOf(it[0], it[1]))%mod }
        return result
    }

    private fun helper(instructions: IntArray, sorted:IntArray, start:Int, end:Int, count:Array<IntArray>){
        if(start>=end) return

        val mid = start + (end-start)/2
        helper(instructions, sorted, start, mid, count)
        helper(instructions, sorted, mid+1, end, count)

        for(i in start .. mid){
            count[i][LEFT] += sorted.lowerBound(instructions[i], mid+1, end) - (mid+1)
            count[i][RIGHT] += (mid - i) + (end - sorted.upperBound(instructions[i], mid+1, end)+1)
        }
        sorted.sort(start, end+1)
    }

    private fun IntArray.lowerBound(target:Int, start:Int, end:Int):Int{
        var head = start
        var tail = end

        while (head<=tail){
            val mid = (head + end)/2
            if(this[mid]<target){
                head = mid+1
            }else{
                tail = mid-1
            }
        }
        return head
    }

    private fun IntArray.upperBound(target:Int, start:Int, end:Int):Int{
        var head = start
        var tail = end

        while (head<=tail){
            val mid = (head + end)/2
            if(this[mid]>target){
                head = mid+1
            }else{
                tail = mid-1
            }
        }
        return head
    }
}