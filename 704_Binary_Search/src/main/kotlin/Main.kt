fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var head = 0
        var tail = nums.lastIndex
        while (head<=tail){
            val mid = (head + tail)/2
            if(nums[mid] == target)
                return mid
            else if(nums[mid]<target)
                head = mid+1
            else
                tail = mid-1
        }
        return -1
    }
}