import java.util.Stack

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun isValid(s: String): Boolean
}

class Solution:Sol {
    override fun isValid(s: String): Boolean {
        var isValid = true
        val map = mapOf(')' to '(',
            '}' to '{',
            ']' to '['
        )
        val stack = Stack<Char>()
        for(char in s){
            if(!isValid) break
            when(char){
                '(','{','[' -> stack.push(char)
                ')','}',']' -> {
                    if(stack.isEmpty() || stack.pop() != map[char]){
                        isValid = false
                    }
                }
            }
        }

        if(!isValid) return false

        isValid = stack.isEmpty()
        return isValid
    }
}