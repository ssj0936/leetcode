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
    println('a'.toInt())
    println('z'.toInt())
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
        val pSize = p.length
        val mapP = Array(26){0}
        for(char in p) ++mapP[charToIntConverter(char)]

        val result = mutableListOf<Int>()
        val mapS = Array<Int>(26){0}
        for(index in s.indices){
            ++mapS[charToIntConverter(s[index])]
            if(index>=pSize)
                --mapS[charToIntConverter(s[index-pSize])]

            if(index>=pSize-1 && mapP.contentDeepEquals(mapS)){
                result.add(index-pSize+1)
            }
        }

        return result
    }

    private fun charToIntConverter(char:Char):Int{
        return char - 'a'
    }
}

class SolutionSlidingWindow2:Sol {
    override fun findAnagrams(s: String, p: String): List<Int> {
        if(p.length>s.length)
            return listOf()
        val pSize = p.length
        val mapP = Array(26){0}
        for(char in p) ++mapP[charToIntConverter(char)]

        //mapP裡裝的就是windows中所需的元素個數
        //討論最順利的狀況：進來的p.lenth位剛好組成p的anagram，map的分數全部被扣掉，全都是0
        //假設進來一個髒東西，map會被記一點，所以只要window裡面有這個東西，一定不會全部為0，一定要index一直走，把它排除在window外他才會被扣掉，window的寬度剛好就p.length
        val result = mutableListOf<Int>()
        for(index in s.indices){
            --mapP[charToIntConverter(s[index])]
            if(index>=pSize)
                ++mapP[charToIntConverter(s[index-pSize])]

            if(index>=pSize-1 && mapP.all { it==0 }){
                result.add(index-pSize+1)
            }
        }

        return result
    }

    private fun charToIntConverter(char:Char):Int{
        return char - 'a'
    }
}