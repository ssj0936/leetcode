class Solution {
    fun swap(nums: IntArray, a:Int, b:Int){
        val tmp = nums[a]
        nums[a] = nums[b]
        nums[b] = tmp
    }

    fun nextPermutation(nums: IntArray): Unit {
        var validBoundIndex = nums.lastIndex
        while (validBoundIndex>0 && nums[validBoundIndex]<=nums[validBoundIndex-1])
            --validBoundIndex

        //re-arrange whole arr
        if(validBoundIndex!=0){
            val target = nums[validBoundIndex-1]
            var left = validBoundIndex
            var right = nums.lastIndex
            while (left<=right){
                val mid = (left + right)/2
                if(nums[mid]<=target){
                    right = mid -1
                }else{
                    left = mid+1
                }
            }

            val index = left

            swap(nums, index-1, validBoundIndex-1)
        }

        //sort from validBoundValue-1 .. num.lastIndex
//        nums.sort(validBoundIndex, nums.size)
        nums.reverse(validBoundIndex, nums.size-1)
    }

    private fun IntArray.reverse(from:Int, to:Int){
        var start = from
        var end = to
        val mid = (start + end).shr(1)
        while (start<=mid){
            swap(this, start++, end--)
        }
    }
}