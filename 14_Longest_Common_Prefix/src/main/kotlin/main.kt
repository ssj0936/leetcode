/*
14. Longest Common Prefix(Easy)

Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.

 */

class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        var prefix = strs[0]

        for(index in 1 until strs.size){
            while(strs[index].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length - 1)
//                println(prefix)
            }
            if(prefix.isEmpty()) break
        }
        return prefix
    }

    fun longestCommonPrefix2(strs: Array<String>): String {
        var prefix = ""

        //一樣是以strs[0]為基準，每個字元拆開strs[0][i]，逐個和其他string比對
        //只要i比某個字串還長 或是某個字串的[i]跟strs[0][i]不相同，就可以停了
        for(i in 0 until strs[0].length){
//            println("i:$i")
            for(j in 0 until strs.size){
//                println("j:$j")
                if(i>=strs[j].length || strs[0][i] != strs[j][i])
                    return prefix
            }
            prefix += strs[0][i]
        }

        return prefix
    }
}

fun main(args: Array<String>) {
    val input = arrayOf("flower","flow","flight")
    val result = Solution().longestCommonPrefix2(input)
    println(result)
}