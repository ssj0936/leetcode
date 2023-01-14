fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}
interface Sol {
    fun findMin(nums:IntArray):Int
}

/*
題目有給了提示，time complexity是O(log n)
用類似binary search的方法
每次頭尾以及中間值去比較
會有幾種狀況：
1. 頭 尾同一個 或是 隔一個，那就兩個取最小的
2. 頭 尾 中間值為順序排列，那就取投return
3. 頭<中間值 但中間值>尾，代表目標在後段，把頭pointer = mid
3. 中間值<尾 但頭>中間值，代表目標在前段，把尾pointer = mid

 */
class Solution:Sol{
    override fun findMin(nums:IntArray):Int{
        var head = 0
        var tail = nums.lastIndex

        while (true){
            val mid:Int = (head + tail)/2

            if(tail - head<=1)
                return Math.min(nums[head],nums[tail])
            else if(nums[head]<nums[mid] && nums[mid]<nums[tail])
                return nums[head]
            else if(nums[head]<nums[mid] && nums[mid] > nums[tail])
                head = mid
            else if(nums[head]>nums[mid] && nums[mid] < nums[tail])
                tail = mid
        }

        return -1
    }
}

/*
我永遠學不會的 更簡潔的做法
 */
class SolutionOthers:Sol {
    override fun findMin(nums: IntArray): Int {
        var l = 0
        var r = nums.size-1
        while(r > l) {
            val m = l + (r - l) / 2
            if(nums[m] > nums[r]) l = m + 1
            else r = m
        }
        return nums[l]
    }
}
