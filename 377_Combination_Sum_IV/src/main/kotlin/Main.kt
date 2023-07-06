fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution{
    private val memo = IntArray(1001){-1}
    fun combinationSum4(nums: IntArray, target: Int): Int {
        if(memo[target]!=-1) return memo[target]

        var sum = 0

        for(num in nums){
            val diff = target - num
            if(diff<0)
                continue
            else if(diff == 0)
                ++sum
            else
                sum += combinationSum4(nums, diff)
        }
        memo[target] = sum
        return sum
    }
}
