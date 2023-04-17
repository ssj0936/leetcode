fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val maxCount = run{
            var max = 0
            candies.forEach { max = Math.max(it,max) }
            max
        }
        val buffer = maxCount - extraCandies
        return candies.map { it>=buffer }
    }
}