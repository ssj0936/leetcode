import java.util.*
import kotlin.collections.HashMap

/*
17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun letterCombinations(digits: String): List<String>
}

class Solution:Sol {
    val map = HashMap<Int, List<String>>().apply {
        put(2, listOf("a","b","c"))
        put(3, listOf("d","e","f"))
        put(4, listOf("g","h","i"))
        put(5, listOf("j","k","l"))
        put(6, listOf("m","n","o"))
        put(7, listOf("p","q","r","s"))
        put(8, listOf("t","u","v"))
        put(9, listOf("w","x","y","z"))
    }

    override fun letterCombinations(digits: String): List<String> {
        val result = mutableListOf<String>()

        if(digits.isEmpty())
            return result

        var outputSize = 1
        val sizeList = mutableListOf<Int>()
        for(digit in digits){
            outputSize *= map[digit.toNumber()]!!.size
            sizeList.add(map[digit.toNumber()]!!.size)
        }

        for(i in 0 .. outputSize-1){
            var tmp = i
            var subResult = ""
            for(j in digits.indices){
                var char = digits[j].toNumber()

                val sizeInThisDigit:Int = sizeList[i]
                val index = tmp % sizeInThisDigit
                val r = map[char]!!.get(index)
                subResult+=r
                tmp /= sizeInThisDigit
            }
            result.add(subResult)
        }

        return result
    }

    private fun Char.toNumber():Int{
        return this.toInt()-48
    }
}

class Solution02:Sol {
    private val map = HashMap<Char, List<String>>().apply {
        put('2', listOf("a","b","c"))
        put('3', listOf("d","e","f"))
        put('4', listOf("g","h","i"))
        put('5', listOf("j","k","l"))
        put('6', listOf("m","n","o"))
        put('7', listOf("p","q","r","s"))
        put('8', listOf("t","u","v"))
        put('9', listOf("w","x","y","z"))
    }

    override fun letterCombinations(digits: String): List<String> {

        if(digits.isEmpty())
            return mutableListOf()

        val queue = LinkedList<String>().apply {
            add("")
        }
//        result.add("")
        for(digitIndex in digits.indices){
            val digit = digits[digitIndex]
            val resultSize = queue.size
            repeat(resultSize) {
                val base = queue.poll()
                for (char in map[digit]!!) {
                    val subResult:String = base.substring(0).plus(char)
                    queue.add(subResult)

                }
            }
        }

        return queue.toList()
    }
}

class SolutionRecursive:Sol {
    private val map = mapOf('2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl", '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz")
    private val result = mutableListOf<String>()
    override fun letterCombinations(digits: String): List<String> {

        if(digits.isEmpty())
            return mutableListOf()

        foo(digits, 0, "")
        return result
    }

    private fun foo(digits: String, digitIndex:Int, subResult:String){
        if(digitIndex == digits.length){
            result.add(subResult)
            return
        }

        for(digit in map.getValue(digits[digitIndex])){
            foo(digits, digitIndex+1, subResult+digit)
        }
    }
}