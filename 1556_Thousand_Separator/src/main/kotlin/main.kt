import java.lang.StringBuilder

/*
1556. Thousand Separator(Easy)
Given an integer n, add a dot (".") as the thousands separator and return it in string format.

Example 1:
Input: n = 987
Output: "987"

Example 2:
Input: n = 1234
Output: "1.234"

Example 3:
Input: n = 123456789
Output: "123.456.789"

Example 4:
Input: n = 0
Output: "0"

Constraints:

0 <= n < 2^31
 */

class Solution {
    fun thousandSeparator(n: Int): String {
        if(n < 1000) return "$n"
        else{
            var result = ""
            var n = n
            while (n>1000){
                val numToString = n % 1000
                var string = "$numToString"
                if(numToString<10)
                    string = "00$string"
                else if(numToString<100)
                    string = "0$string"

                result = if(result.isNotEmpty())
                    "$string.$result"
                else
                    "$string"
                n /= 1000
            }
            result = "$n.$result"
            return result
        }
    }
}

fun main(args: Array<String>) {
    val value = 51040
    val result = Solution().thousandSeparator(value)

    println(result)
}