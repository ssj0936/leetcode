fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun longestPalindromeSubseq(s: String): Int
}

class Solution:Sol {
    override fun longestPalindromeSubseq(s: String): Int {
        val dp = Array(s.length){IntArray(s.length){1} }
        for(i in s.lastIndex downTo 0){
            for(j in i .. s.lastIndex){
                if(i == j) {}

                else if(s[i]==s[j]){
                    if(j-i==1)
                        dp[i][j] = 2
                    else
                        dp[i][j] = dp[i+1][j-1]+2
                }else{
                    if(j-i==1)
                        dp[i][j] = 1
                    else
                        dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j])
                }

                println("dp[$i][$j]:${dp[i][j]}")
            }
        }

        return dp[0][s.lastIndex]
    }
}