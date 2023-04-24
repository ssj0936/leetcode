fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val lt1 = text1.length
        val lt2 = text2.length
        val dp = Array(lt1+1){IntArray(lt2+1)}

        for(i in 1 .. lt1){
            for(j in 1 .. lt2){
                val indexI = i-1
                val indexJ = j-1
                dp[i][j] = if(text1[indexI]==text2[indexJ]){
                    dp[i-1][j-1]+1
                } else {
                    maxOf(dp[i-1][j], dp[i][j-1])
                }
            }
        }

        return dp[lt1][lt2]
    }
}