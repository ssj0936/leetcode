/**
 * 125. Valid Palindrome
Easy
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

Constraints:

1 <= s.length <= 2 * 10^5
s consists only of printable ASCII characters.

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("${'A'.toInt()}")
    println("${'Z'.toInt()}")
    println("${'a'.toInt()}")
    println("${'z'.toInt()}")
}

interface Sol{
    fun isPalindrome(s: String): Boolean
}

//失敗作 無法assert空字串的部分
class Solution:Sol {
    override fun isPalindrome(s: String): Boolean {
        var head = 0
        var tail = s.lastIndex

        while (head<tail){
            while (!isAlphabet(s[head]) && head<=s.lastIndex)
                ++head

            while (!isAlphabet(s[tail]) && tail>=0)
                --tail

            println("s[$head]:${s[head]},s[$tail]:${s[tail]},")

            if(!isSameIgnoreCase(s[head], s[tail]))
                return false

            if(head == tail)
                return true

            if(head > tail)
                return true

            ++head
            --tail
        }

        return false
    }

    private fun isSameIgnoreCase(a:Char, b:Char):Boolean{
        if(a.toInt() == b.toInt())
            return true

        if(a.toInt() > b.toInt() && a-32 == b)
            return true

        if(a.toInt() < b.toInt() && b-32 == a)
            return true

        return false
    }

    private fun isAlphabet(c:Char):Boolean{
        //A = 65, Z = 90, a = 97, z = 122
        return c.toInt() in 65 ..90 || c.toInt() in 97 .. 122
    }
}

class Solution01:Sol {
    override fun isPalindrome(s: String): Boolean {
        var head = 0
        var tail = s.lastIndex

        while (head<tail){
            while (getChar(s[head])==-1 && head<tail && head<=s.lastIndex )
                ++head

            while (getChar(s[tail])==-1 && head<tail && tail>=0)
                --tail
            println("s[$head]:${s[head]},s[$tail]:${s[tail]},")

            if(getChar(s[head])!=getChar(s[tail]))
                return false

            ++head
            --tail
        }
        return true
    }

    /*
    a~z : 97~122
    A~Z : 65~90
    0~9 : 48~57
     */
    private fun getChar(c:Char):Int{
        var charInt = c.toInt()
        if(charInt in 65 .. 90 || charInt in 48..57)
            return charInt
        else if(charInt in 97..122)
            return charInt-32
        //not a char
        return -1
    }
}

/*
優雅的解法
配合用when，裡外共用同一個while
equals同時使用ignore case
 */
class Solution03 {

    fun isPalindrome(input: String): Boolean {
        var start = 0
        var end = input.length - 1
        while (start <= end) {
            when {
                !isAlphabet(input[start]) -> start++
                !isAlphabet(input[end]) -> end--
                input[start].equals(input[end], true) -> {
                    start++
                    end--
                }
                else -> return false
            }

        }
        return true
    }

    private fun isAlphabet(char: Char) = char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9'
}
