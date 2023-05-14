fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    private val mod = 1000000007
    fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
        val dp = IntArray(high+1)
        var result = 0

        for(i in 1 .. high){
            dp[i] = ((if(i<zero) 0 else dp[i-zero]) + (if(i<one) 0 else dp[i-one]))%mod

            if(i == zero)
                dp[i] = (dp[i]+1)%mod
            if(i == one)
                dp[i] = (dp[i]+1)%mod

//            dp[i] %= mod

            if(i in low..high)
                result = (dp[i] + result)%mod
        }
//        println(dp.contentToString())

        return result
    }
}