import java.util.*
import kotlin.collections.HashSet

class SolutionBrutal {
    val result = mutableListOf<String>()
    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        val stack = LinkedList<String>()
        helper(s, 0, wordDict, stack)
        return result
    }

    private fun helper(s:String,index:Int, wordDict: List<String>, stack:LinkedList<String>){
        if(index>=s.length){
            if(index==s.length)
                result.add(stack.reversed().joinToString(" "))
            return
        }

        wordDict.filter{it[0]==s[index] && isFit(s, index, it)}.forEach{ word ->
            stack.push(word)
            helper(s, index + word.length, wordDict, stack)
            stack.pop()
        }
    }

    private fun isFit(s: String, start:Int, word:String):Boolean{
        if(start>=s.length) return false
        if(start+word.lastIndex>=s.length) return false

        for(i in 0 .. word.lastIndex){
            if(s[start + i] != word[i]) return false
        }
        return true
    }
}

class Solution {
    val memo = hashMapOf<Int,MutableList<String>>()
    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        return helper(s, 0, wordDict)
    }

    private fun helper(s:String,index:Int, wordDict: List<String>):List<String>{
        memo.get(index)?.let {
            return it
        }

        if(index>=s.length){
            return emptyList()
        }

        val result = mutableListOf<String>()
        for(word in wordDict){
            if(!isFit(s, index, word)) continue

            if(index+word.lastIndex == s.lastIndex) {
                result.add(word)
            }else {
                val remains = helper(s, index + word.length, wordDict)

                if (remains.isNotEmpty()) {
                    remains.forEach {remain->
                        val subResult = word + if (remain.isNotEmpty()) " $remain" else ""
                        result.add(subResult)
                    }
                }
            }
        }
        result.forEach{
            memo.getOrPut(index){ mutableListOf()}.add(it)
        }
        return result
    }

    private fun isFit(s: String, start:Int, word:String):Boolean{
        if(start>=s.length) return false
        if(start+word.lastIndex>=s.length) return false

        for(i in 0 .. word.lastIndex){
            if(s[start + i] != word[i]) return false
        }
        return true
    }
}