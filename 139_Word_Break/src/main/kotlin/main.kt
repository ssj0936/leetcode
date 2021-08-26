/*
139. Word Break(Medium)
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.

 */
class Solution {
    private val hashMap = Array<MutableList<String>>(26){ mutableListOf()}

    /*
    暴力法，會超過時間的方法
    從頭開始去對dict，有對到就裁掉 並且往後recursive去找
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {

        wordDict.forEach { word ->
            val c = word.first()
            hashMap[c-'a'].add(word)
        }
        val startIndex = 0
        return recursive(s,startIndex)
    }
    private fun recursive(s: String, startIndex:Int):Boolean{
        println("startIndex:$startIndex")
        if(startIndex == s.lastIndex+1) return true

        val firstC = s[startIndex]
        hashMap[firstC-'a'].forEach { word ->
            if(s.indexOf(word,startIndex = startIndex) == startIndex && recursive(s,startIndex + word.length)) {
                return true
            }
        }
        return false
    }

    /*
    用DP解，小訣竅，看到子字串或是子序列求極值 可以考慮一下DP解

    maintain一個DP，DP[i](boolean)代表s中[0,i)可以被完美切割(0以上 不包含i)，DP[s.length] 就是解
    DP[0] = true(空字串)
    用2-pointer，i從頭到尾，j則是從頭到i，代表每輪[0,i)都會切成一個子字串，而j就是在0,i之間游動
    同時也會把這子字串再切成兩部分，[0,j)和[j,i)，[0,j)我們可以從DP比看出來這部分能不能被完美切割，所以接下來就是看[0,j)有沒有再DICT裡
    兩者都有就皆大歡喜DP[i]直接填true並且i直接往下做，j如果跑完一輪 都不能湊成兩者皆true，DP[i]田fault
    DP[s.length] 就是解
     */
    fun wordBreakV2(s: String, wordDict: List<String>): Boolean {

        val dp = Array(s.length+1){false}
        //空字串
        dp[0] = true

        for(i in 0 .. s.length){
//            println("i=$i -------------")
            for(j in 0 .. i){
//                println("${s.substring(0,j)},${s.substring(j,i)}")
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true
                    break
                }
            }
        }
        println(dp.contentToString())

        return dp[s.length]
    }
}
fun main() {
    val s = "applepenapple"
    val wordDict = listOf("apple","pen")
    val result = Solution().wordBreakV2(s,wordDict)
    println(result)
}