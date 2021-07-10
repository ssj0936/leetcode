// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

// Only one letter can be changed at a time.
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// Note:

// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.

// Example 1:
// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]

// Output: 5
// Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.

// Example 2:
// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]

// Output: 0
// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

class Solution127 {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if(!wordList.contains(endWord)) return 0
        
        val map = wordList.toMutableSet();
        // 用queue紀錄每次繼續往下走的起點
        val queue = mutableListOf<String>()
        var height = 0
        
        queue.add(beginWord)
        while(queue.size != 0){
            ++height
            for(c in 0..queue.size-1){
                var word = queue.removeAt(0)
                for (charI in 0..word.length-1){
                    for (a in 'a'..'z'){
                        if(word[charI] == a) continue

                        val tmp = word.replaceRange(charI,1+charI,""+a)
                        if(tmp == endWord)
                            return height+1
                        if(map.contains(tmp)){
                            queue.add(tmp)
                            // map.remove(tmp)
                        }
                    }
                }
            }
        }
        return 0
    }
}

fun main(args: Array<String>) {
    val wordList:List<String> = listOf("hot","dot","dog","lot","log","cog")
    val beginWord:String = "hit"
    val endWord:String = "cog"
    // val sol = Solution()
    var result = Solution127().ladderLength(beginWord,endWord,wordList)
    println(result)
}