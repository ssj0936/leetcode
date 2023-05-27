fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun stoneGameIII(stoneValue: IntArray): String {
        val n = stoneValue.size
        val dp = Array(n){IntArray(3+1)}

        var sum = 0
        for(i in n-1 downTo 0){
            sum += stoneValue[i]
            for(m in 1 .. 3){
                if(i == n-1)
                    dp[i][m] = sum
                else if(i+m >= n)
                    dp[i][m] = sum
                else{
                    dp[i][m] = sum - maxOf(dp[i+m][1],dp[i+m][2],dp[i+m][3])
                }
            }
        }

        val scoreAlice = maxOf(dp[0][1],dp[0][2],dp[0][3])
        val scoreBob = sum - scoreAlice

        return if (scoreAlice==scoreBob) "Tie" else if(scoreAlice>scoreBob) "Alice" else "Bob"
    }
}