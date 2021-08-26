/*
763. Partition Labels(Medium)
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
Return a list of integers representing the size of these parts.

Example 1:
Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

Example 2:
Input: s = "eccbbbbdec"
Output: [10]

Constraints:
1 <= s.length <= 500
s consists of lowercase English letters.

思路：
1.把每個字母最後出現的位置記錄下來(hashmap)
2.建一個set，並從字串開頭loop，每次抓一個字
    如果該char不在set裡面(而且這個char的起點不是終點) -> 把char加進set
    如果這個char在set裡，檢查這一點是不是終點
        不是終點就繼續做
        是終點，就把他剃除並檢查set是否為空，為空則代表一個part的結束

第二個part看discussion發現，可以以slide window方式進行
以一個pivot代表目前掃過的char中最遠的終點，邊掃邊更新這個pivot值
直到index = pivot為止，斷掉形成一個part
 */

class Solution {
    fun partitionLabels(s: String): List<Int> {
        val result = mutableListOf<Int>()
        //紀錄每個字母的最後一次出現位置
        val hashMap = HashMap<Char,Int>()

        for(index in s.length-1 downTo 0){
            if(hashMap[s[index]] == null){
                hashMap[s[index]] = index
                if(hashMap.size >= 26) break
            }
        }

        val set = mutableSetOf<Char>()
        for((index,c) in s.withIndex()){
            if(!set.contains(c) && hashMap[c]!=index) set.add(c)
            else{
                if(hashMap[c]==index){
                    set.remove(c)
                    if(set.isEmpty()) {
                        result.add(index + 1)
                    }
                }
            }
        }

        for(i in result.size-1 downTo 0){
            if(i != 0) result[i] = result[i] - result[i-1]
        }

        return result
    }

    fun partitionLabelsV2(s: String): List<Int> {
        val result = mutableListOf<Int>()
        //紀錄每個字母的最後一次出現位置
        val hashMap = HashMap<Char,Int>()

        for(index in s.length-1 downTo 0){
            if(hashMap[s[index]] == null){
                hashMap[s[index]] = index
                if(hashMap.size >= 26) break
            }
        }

        var start = 0
        var maxIndex = 0
        for ((index,c) in s.withIndex()){
            maxIndex = Math.max(maxIndex, hashMap[c]!!)

            if(maxIndex == index){
                result.add(index - start + 1)
                start = index + 1
            }
        }

        return result
    }
}
fun main(args: Array<String>) {
    val s = "ababcbacadefegdehijhklij"
    val result = Solution().partitionLabelsV2(s)
    println(result.toString())
}