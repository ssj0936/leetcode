fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class SolutionDP {
    fun maxProfit(prices: IntArray): Int {
        val dp = IntArray(prices.size)

        for(i in 1 ..prices.lastIndex){
            var maxProfit = maxOf(0, prices[i] - prices[0])
            for(j in 0 until i){
                val tmpProfit = dp[j] + (prices[i] - prices[j+1])
                if(tmpProfit>maxProfit)
                    maxProfit = tmpProfit
            }
            dp[i] = maxProfit
        }
        return dp[prices.lastIndex]
    }
}

class Solution {
    fun maxProfit(prices: IntArray): Int {
        var maxP = 0
        var valleyPtr = 0
        for(p in 1 .. prices.lastIndex){
            if(prices[p] < prices[p-1]) {
                maxP += prices[p-1] - prices[valleyPtr]
                valleyPtr = p
            }
        }

        maxP += maxOf(0, prices.last() - prices[valleyPtr])

        return maxP
    }
}