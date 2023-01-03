/**
 * 76. Minimum Window Substring
Hard
Given two strings s and t of lengths m and n respectively, return the minimum window
substringof s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
    println("A:${'A'.toInt()}, Z:${'Z'.toInt()}, a:${'a'.toInt()}, z:${'z'.toInt()}")
}

interface Sol{
    fun minWindow(s: String, t: String): String
}

/*
https://leetcode.com/problems/minimum-window-substring/solutions/164122/official-solution/

sliding window 解，sliding window 可以想像成是左右邊界，移動其中一個邊界觸發條件後(打到標準)停止，換移動另一個邊界
這邊用頭front 尾tail兩個邊界，加上t中字母數量(cnt)
1. 移動尾巴 移動過程中要是經過t中的字母，cnt--，直到cnt==0時 代表目前頭尾之間的substring是包含了t中所有的字
1.5 這時可以先記錄一下substring的內容以及length
2. 換移動頭，移動過程中如果經過t中的字母，由於我們剛剛減掉了，現在加回來(cnt++)，只要cnt還是維持0就可以一直內縮頭的pointer壓縮字串(代表頭尾之間的substring還是符合條件包含所有字串)
3. 直到cnt!=0 回到step1

過程中，利用hashmap<Char,Int>來紀錄有沒有經過t中的字母
所以可以變成：
0. 初始化hashmap 代表t中每個字母的個數
1. 移動尾巴 移動過程中要是經過t中的字母c，map[c]--以及cnt--，直到cnt==0時 代表目前頭尾之間的substring是包含了t中所有的字
1.5 這時可以先記錄一下substring的內容以及length
2. 換移動頭，移動過程中如果經過t中的字母c，由於我們剛剛減掉了，所以現在加回來map[c]++以及cnt++，只要cnt還是維持0，就可以一直內縮頭的pointer壓縮字串(代表頭尾之間的substring還是符合條件包含所有字串)
3. 直到cnt!=0 回到step1

 */
class Solution:Sol {
    override fun minWindow(s: String, t: String): String {
        var front = 0
        var count = t.length

        var minLen = Int.MAX_VALUE
        var result = ""

        //convert t into table
        val table = Array('z'.toInt()+1){0}
        t.forEach { char->
            ++table[char.toInt()]
        }

        for(tail in s.indices){
//            println("tail:$tail")
            if(--table[s[tail].toInt()]>=0)
                --count
            while(count==0){
                if(minLen> tail - front +1){
                    minLen = tail - front +1
                    result = s.substring(front, front + minLen)
//                    println("minLen:$minLen, result:$result")
                }

                if(++table[s[front].toInt()]>0)
                    ++count

//                println(s.substring(front..tail))
                ++front
            }
        }
        return result
    }
}