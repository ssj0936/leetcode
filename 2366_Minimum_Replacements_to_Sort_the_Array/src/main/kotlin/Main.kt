import java.util.*

class Solution {
    fun minimumReplacement(nums: IntArray): Long {

        val newNums = nums.reversed().toMutableList().apply { this.add(0) }
        var max = Int.MAX_VALUE

        var totalOps:Long = 0
        for(num in newNums){
            if(num<=max)
                max = num
            else{
                var operation = num/max
                var remain = num % max
                if(remain == 0){
                    remain = max
                    --operation
                }

                max = remain
                totalOps += operation
            }
        }

        return totalOps
    }
}

class Solution {
    private var totalOps:Long = Long.MAX_VALUE
    fun minimumReplacement(nums: IntArray): Long {

        helper(nums, nums.lastIndex, 0, Int.MAX_VALUE)
        return totalOps

    }

    private fun helper(nums: IntArray, i:Int, ops:Long, prev:Int){
        if(i < 0){
            totalOps = ops
            return
        }

        if(nums[i]<=prev) {
            helper(nums, i - 1, ops, nums[i])
        }else if(nums[i]%prev == 0){
            helper(nums, i-1, ops + (nums[i]/prev-1), prev)
        }else{
            helper(nums, i-1, ops + (nums[i]/prev), nums[i]/(nums[i]/prev +1))
        }
    }
}