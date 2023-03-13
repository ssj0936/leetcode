import kotlin.math.pow

/*
224. Basic Calculator
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23

Constraints:

1 <= s.length <= 3 * 10^5
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.

 */

interface Sol{
    fun calculate(s: String): Int
}

class Solution {
    private val stack = ArrayDeque<Any>()
    fun calculate(s: String): Int {
        var pointer = 0
        while (pointer < s.length){
            if(s[pointer] == ' ')
                continue

            when(s[pointer]){
                '+' -> {
                    stack.push(s[pointer])
                }
                '-' ->{
                    if(stack.peek() !is Int)
                        stack.push(0)
                    stack.push(s[pointer])
                }
                '(' -> {
                    stack.push(s[pointer])
                }
                ')' -> {
                    var stackTop = stack.pop()
                    var tmp = stackTop as Int

                    while (stackTop != '('){
                        if(stackTop is Char){
                            var numberFirst = stack.pop() as Int
                            if(stack.peek() == '-') {
                                numberFirst *=-1
                                stack.pop()
                                stack.push('+')
                            }
                            tmp = operate(stackTop, numberFirst, tmp)
                        }

                        stackTop = stack.pop()
                    }

                    //push back final result
                    stack.push(tmp)
                }
                else -> {
                    // first. combine to num
                    val numCombiner = ArrayDeque<Int>().apply {
                        push(s[pointer] - '0')
                    }
                    while (pointer+1 <= s.lastIndex && s[pointer+1] in '0'..'9'){
                        ++pointer
                        numCombiner.push(s[pointer] - '0')
                    }

                    var num = 0
                    var count:Double = 0.0
                    while (numCombiner.isNotEmpty()){
                        num += numCombiner.pop() * Math.pow(10.0, count++).toInt()
                    }

                    stack.push(num)
                }
            }
            ++pointer
        }

        if(stack.size > 1){
            var stackTop = stack.pop()
            var tmp = stackTop as Int

            while (stack.peek() is Char){
                stackTop = stack.pop() as Char
                var numberFirst = stack.pop() as Int
                if(stack.peek() == '-') {
                    numberFirst *=-1
                    stack.pop()
                    stack.push('+')
                }
                tmp = operate(stackTop, numberFirst, tmp)
            }
            //push back final result
            stack.push(tmp)
        }

        return stack.pop() as Int
    }

    private fun <T> ArrayDeque<T>.push(el:T) = this.addLast(el)
    private fun <T> ArrayDeque<T>.pop() = this.removeLast()
    private fun <T> ArrayDeque<T>.peek() = this.lastOrNull()

    private fun operate(operator:Char, num1:Int, num2:Int):Int{
        return if(operator=='+')
                num1+num2
            else
                num1-num2
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}