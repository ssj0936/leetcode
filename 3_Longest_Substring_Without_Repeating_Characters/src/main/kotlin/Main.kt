/*
3. Longest Substring Without Repeating Characters
Medium
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:

0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.

 */

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0

        val hashMap = HashMap<Char, Int>()
        var head = 0
        for(tail in 0 .. s.lastIndex){
            val headNeedToMoveTo = hashMap.get(s[tail])?:-1
            if(headNeedToMoveTo>=head)
                head = headNeedToMoveTo+1
            hashMap.put(s[tail], tail)
            maxLength = Math.max(maxLength, tail-head+1)
        }

        return maxLength
    }

    fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0

        val set = mutableSetOf<Char>()
        var head = 0
        println(s)
        for(tail in 0 .. s.lastIndex){
            println("s[tail]:${s[tail]}, set:$set")
            if(!set.contains(s[tail])) {
                println("situ 1")
                set.add(s[tail])
                maxLength = Math.max(maxLength, tail-head+1)
            }else{
                println("situ 2")
                while (s[head] != s[tail]){
                    set.remove(s[head])
                    ++head
                }
                set.remove(s[head])
                ++head
                set.add(s[tail])
            }
        }

        return maxLength
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}