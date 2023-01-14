/**
 * 211. Design Add and Search Words Data Structure
Medium
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:
WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 3 dots in word for search queries.
At most 10^4 calls will be made to addWord and search.

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

/*
其實跟trie那題差不多 但差在有'.'這個萬用字元
就變成說 search比對過程中，有對中或是比對的對象是'.'那就往下

遞迴的結束條件是：比對完整條string都有正確，而且最後trie的node是leaf，return true
遞迴fun中的wordIndex表示word中這一輪指到的index，遞迴中的p表示trie中比對的node
但傳到下一輪中的wordIndex都會先行+1，如果再fun開頭做遞迴終止條件審查的話 需要把index退一格

為了美觀 所以把終止條件檢查寫在前一輪中
 */
class WordDictionary() {
    data class Node(val char: Char=' ', var nextMap:HashMap<Char, Node> = HashMap(), var isLeaf:Boolean = false)
    private val root = Node()

    fun addWord(word:String){
        var pointer = root
        for(c in word){
            pointer = pointer.nextMap.getOrPut(c){ Node(c) }
        }
        pointer.isLeaf = true
    }

    fun search(word:String):Boolean{
        return bfs(word,0,root)
    }

    private fun bfs(word:String, wordCurrIndex:Int, p:Node):Boolean{
        for((_, nextNode) in p.nextMap){

            if(word[wordCurrIndex] == nextNode.char || word[wordCurrIndex]=='.'){

                //遞迴的結束條件
                if(wordCurrIndex == word.lastIndex
                    && nextNode.isLeaf
                    && (word[wordCurrIndex] =='.'||word[wordCurrIndex] == nextNode.char)) {
                    return true
                }

                //繼續遞迴
                //wordCurrIndex < word.lastIndex這個條件，是為了像是trie裡面塞了pad, man, dad,然後要search "a"，為了避免indexOutOfBoundary
                if(wordCurrIndex < word.lastIndex && bfs(word, wordCurrIndex+1, nextNode)){
                    return true
                }
            }
        }
        return false
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * var obj = WordDictionary()
 * obj.addWord(word)
 * var param_2 = obj.search(word)
 */