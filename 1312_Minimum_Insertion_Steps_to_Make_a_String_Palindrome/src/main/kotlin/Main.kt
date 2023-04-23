fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun minInsertions(s: String): Int
}

class Solution:Sol {
    override fun minInsertions(s: String): Int {
        val table = IntArray(26)
        s.forEach { ++table[it.toIndex()] }

        var step = 0
        var firstOddFlag = false
        for(charCount in table){
            if(charCount % 2==1){
                if(!firstOddFlag) {
                    firstOddFlag = true
                    continue
                }
                else{
                    ++step
                }
            }
        }
        return step
    }

    private fun Char.toIndex():Int = this-'a'
}

/*
hint解法，s.length - PalindromeSubseq.length，我覺得概念很糢糊不太容易懂，但大家都submit這個解，idk why
 */
class Solution02:Sol {
    override fun minInsertions(s: String): Int {
        val dp = Array(s.length){IntArray(s.length)}
        for (i in s.lastIndex downTo 0){
            for(j in i .. s.lastIndex){
                if(s[i]==s[j])
                    dp[i][j] = if(i==j) 1 else if(j-i==1) 2 else dp[i+1][j-1]+2
                else
                    dp[i][j] = maxOf(dp[i+1][j], dp[i][j-1])
            }
        }
        return s.length - dp[0][s.lastIndex]
    }
}

/*
discussion提供的解法，也是DP解
dp[i][j]代表s[i..j]要變成Palindrome所需要插入的字元數
如果s[i]==s[j] 兩者位置相同又或者相鄰那就不需要額外插入=0 否則就是dp[i+1][j-1]
反之s[i]!=s[j] 那就是i..j段去掉頭 或是去掉尾 取插入個數小的，再加1，這個1就是去補上去掉的頭或尾的個數
 */
class Solution03:Sol {
    override fun minInsertions(s: String): Int {
        val dp = Array(s.length){IntArray(s.length)}
        for (i in s.lastIndex downTo 0){
            for(j in i .. s.lastIndex){
                if(s[i]==s[j])
                    dp[i][j] = if(i==j || j-i==1) 0 else dp[i+1][j-1]
                else
                    dp[i][j] = 1+minOf(dp[i+1][j], dp[i][j-1])
            }
        }
        return dp[0][s.lastIndex]
    }
}