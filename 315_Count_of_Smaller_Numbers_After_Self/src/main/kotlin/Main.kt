class SolutionOrigin {
    fun countSmaller(nums: IntArray): List<Int> {
        var result = IntArray(nums.size){0}

        val newList = mutableListOf(nums[nums.lastIndex])
        for(i in nums.lastIndex-1 downTo 0){
            val findTarget = nums[i]
            val searchResult = newList.bs(findTarget)
            result[i] = searchResult
            newList.add(searchResult,findTarget)
        }
        return result.toList()
    }

    private fun List<Int>.bs(target:Int):Int{
        var front = 0
        var tail = this.lastIndex

        while (front<=tail){
            val mid = (front + tail)/2
            if(this[mid]<target){
                front = mid +1
            }else{
                tail = mid -1
            }
        }
        return front
    }
}

class Solution {
    fun countSmaller(nums: IntArray): List<Int> {
        val result = IntArray(nums.size)
        val sorted = nums.clone()
        helper(nums, sorted, 0, nums.lastIndex,result)
        return result.toList()
    }

    private fun helper(nums: IntArray, sorted:IntArray, start:Int, end:Int, result:IntArray){
        if(start>=end)
            return

        val mid = (start + end)/2
        helper(nums, sorted, start, mid, result)
        helper(nums, sorted, mid+1, end, result)

        for(i in start .. mid){
            var indexInSecondPeriod = sorted.lowerBound(nums[i], mid+1, end)
            result[i] += (indexInSecondPeriod - (mid-1))
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
}