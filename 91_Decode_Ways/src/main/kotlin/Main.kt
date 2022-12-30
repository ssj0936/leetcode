/**
 * 91. Decode Ways
Medium
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
Given a string s containing only digits, return the number of ways to decode it.
The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun numDecodings(s: String): Int
}

class Solution:Sol {
    private var count = 0
    private val tableSize = 26
    override fun numDecodings(s: String): Int {
        foo(s, 0)
        return count
    }

    private fun foo(s: String, startIndex:Int){
        if(startIndex >= s.length){
            ++count
            return
        }

        //切一個字元的狀態
        val oneChar = Integer.valueOf(s.substring(startIndex..startIndex))
        if(oneChar in 1..tableSize) {
            foo(s, startIndex + 1)
        }

        //切兩個字元的狀態
        if(startIndex < s.length-1) { //確保可以切兩個字
            val twoChar = Integer.valueOf(s.substring(startIndex..startIndex + 1))
            if (twoChar>=9 && twoChar in 1..tableSize)
                foo(s,startIndex+2)
        }
    }
}
/*
用DP，dp[i]代表結束在i的decode數
 */
class Solution01:Sol {
    override fun numDecodings(s: String): Int {
        if(s.isEmpty() || s.startsWith('0'))
            return 0

        val dp = Array<Int>(s.length){0}
        for(i in s.indices){
            if(i==0){
                if(s[i]!='0')
                    dp[i] = 1
            }else if(i == 1){
                if(s[i]!='0')// 如果自己一位是valid的，decode數+1
                    dp[i] +=1

                if(Integer.valueOf(s.substring(0..i)) in 10..26)//如果前兩位是valid的，decode數+1
                    dp[i] +=1
            }else{
                if(s[i]!='0')
                    dp[i] += dp[i-1]

                if(Integer.valueOf(s.substring(i-1..i))in 10..26)
                    dp[i] += dp[i-2]
            }
        }
        return dp[s.lastIndex]
    }
}

/*
DP解，dp[i]代表前i個數字組合數，但單純覺得很不直覺
 */
class Solution02:Sol {
    override fun numDecodings(s: String): Int {
        if(s.isEmpty() || s.startsWith('0'))
            return 0

        val dp = Array(s.length+1){0}
        dp[0] = 1 //單純方便計算
        for(i in 1 .. s.length){
            dp[i] = if(s[i-1]=='0') 0 else dp[i-1]
            if(i>1 && Integer.valueOf(s.substring(i-2..i-1))in 10..26)
                dp[i] += dp[i-2]
        }
        return dp[s.length]
    }
}