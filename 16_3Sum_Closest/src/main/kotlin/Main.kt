import kotlin.math.abs

class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        if(nums.size == 3) return nums.sum()
        nums.sort()

        var closest = Int.MAX_VALUE
        for(i in 0 .. nums.lastIndex-2){
            for(j in nums.lastIndex-1 downTo i+1){
                val t = target - nums[i] - nums[j]
                var find = nums.binarySearch(t, j+1, nums.size)
                if(find>=0)
                    return target
                else {
                    find = -(find + 1)
                    if(find in j+1 .. nums.lastIndex)
                        closest = minOf(closest, abs(nums[i] + nums[j] +nums[find]) )
                    if(find-1 in j+1 .. nums.lastIndex)
                        closest = minOf(closest, abs(nums[i] + nums[j] +nums[find-1]))

//                    if(find==nums.size)
                }
            }
        }
        return closest
    }
}