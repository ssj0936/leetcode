/*
438. Find All Anagrams in a String
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun findAnagrams(s: String, p: String): List<Int>
}

class Solution:Sol {
    override fun findAnagrams(s: String, p: String): List<Int> {
        if(p.length>s.length)
            return listOf()

        val mapP = HashMap<Char,Int>()
        for(char in p){
            mapP[char] = mapP.getOrDefault(char, 0)+1
        }
        var head = 0
        var tail = 0
        val result = mutableListOf<Int>()

        while (tail<s.length){
            val fullSizeFlag = tail-head == p.lastIndex

            if(!fullSizeFlag && !mapP.containsKey(s[tail])) {
                ++tail
                head = tail
                continue
            }

            if (!fullSizeFlag && mapP.containsKey(s[tail]) && tail<s.lastIndex){
                ++tail
                continue
            }

            if(fullSizeFlag){
                if(isAnagramsInSameLength(s.substring(head..tail), mapP)) {
                    result.add(head)

                    while (tail<s.lastIndex && s[tail+1]==s[head]){
                        ++head
                        ++tail
                        result.add(head)
                    }
                }

                val originTail = tail
                while (tail<s.lastIndex && !mapP.containsKey(s[tail+1])){
                    ++tail
                    head = tail
                }
                if(originTail == tail){
                    ++head
                    ++tail
                }
            }

        }
        return result
    }

    private fun isAnagramsInSameLength(s: String, mapP: HashMap<Char,Int>):Boolean{
        val cloneP:HashMap<Char,Int> = HashMap(mapP)
        for(char in s){
            if(!cloneP.contains(char))
                return false
            cloneP[char] = cloneP[char]!!-1

            if(cloneP[char]!! <0)
                return false
        }

        return true
    }
}

class SolutionSlidingWindow:Sol {
    override fun findAnagrams(s: String, p: String): List<Int> {
        if(p.length>s.length)
            return listOf()

        val mapP = HashMap<Char,Int>()
        for(char in p){
            mapP[char] = mapP.getOrDefault(char, 0)+1
        }
        var head = 0
        var validCount = p.length
        val result = mutableListOf<Int>()

        for(tail in s.indices){
            val charCount = mapP.getOrPut(s[tail]){0}
            if(charCount>0){
                --validCount
            }
            mapP.put(s[tail], charCount-1)

            if(validCount==0)
                result.add(head)

            val charCount2 = mapP.getOrPut(s[head]){0}
            if(tail-head == s.length){
                if(charCount2 >=0){
                    ++validCount
                }
                ++head
                mapP.put(s[head], charCount2+1)

            }
        }

        return result
    }
}