/**
 * 49. Group Anagrams
Medium
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println('a'.code)
    println('b'.code)
    println('c'.code)
    println('d'.code)
    println('z'.code)

    val sol = Solution().countingSort("ajsdningjho")
    println("result = $sol")
}

interface Sol{
    fun groupAnagrams(strs: Array<String>): List<List<String>>
}

/*
思路在於，先把標的sort過 就知道是不是要被歸類在同一個group裡
那決勝點就在於sort的方式，既然已經知道內容物是落在某一區間內 那用counting sort會是最適合的
time complexity = O(n+k)
space complexity = O(n+k)
 */
class Solution:Sol {
    override fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String,MutableList<String>>()
        for(string in strs){
            val sortedResult = countingSort(string)
            if(!map.containsKey(sortedResult))
                map[sortedResult] = mutableListOf()
            map[sortedResult]?.add(string)
        }

        val result = mutableListOf<MutableList<String>>()
        for(entry in map.entries.iterator()){
            result.add(entry.value)
        }
        return result
    }

    fun countingSort(string:String):String{
        val countingTable = IntArray(26){ i->0}
        for(char in string){
            val convertedInt = char.toInt() - 'a'.toInt()
            countingTable[convertedInt]+=1
        }

        //記錄這個字母 第一個應該排在sort好的第幾位
        //所以accumulatingTable[i] = accumulatingTable[i-1] + countingTable[i-1]
        val accumulatingTable = IntArray(26){i->0}
        for(i in 1 until accumulatingTable.size){
            accumulatingTable[i] = accumulatingTable[i-1] + countingTable[i-1]
        }

        val newString = CharArray(string.length)
        for(char in string){
            val insertIndex = accumulatingTable[char.toInt() - 'a'.toInt()]
            newString[insertIndex] = char
            ++accumulatingTable[char.toInt() - 'a'.toInt()]
        }

        return String(newString)
    }
}