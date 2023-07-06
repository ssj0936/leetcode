import java.util.*

class SolutionOri {
    fun calculate(s: String): Int {
        val numStack = LinkedList<Int>()
        val operatorStack = LinkedList<Char>()

        var i = 0
        while (i<s.length){
            when (val char = s[i]) {
                ' ' -> {
                    ++i
                }
                '+', '-', '*', '/' -> {
                    operatorStack.push(char)
                    ++i
                }
                else -> {
                    val builder = StringBuilder().apply { append(s[i++]) }
                    while (i <s.length && s[i].isDigit()){
                        builder.append(s[i])
                        ++i
                    }

                    var number = builder.toString().toInt()

                    while (operatorStack.peek() == '*' || operatorStack.peek()=='/'){
                        val operator = operatorStack.pop()
                        val anotherNumber = numStack.pop()
                        number = if(operator=='*'){anotherNumber * number} else {anotherNumber/number}
                    }

                    numStack.push(number)
                }
            }
        }
        var base = numStack.pollLast()
        while (numStack.isNotEmpty()){
            val operator = operatorStack.pollLast()
            val anotherNumber = numStack.pollLast().toInt()
            base = if(operator=='+'){base + anotherNumber} else {base - anotherNumber}
        }

        return base
    }
}

class Solution {
    fun calculate(s: String): Int {
        var lastOperator = '+'
        val stack = LinkedList<Int>()
        var currentNumber = 0
        s.forEachIndexed { index, c ->

            if(c.isDigit()){
                currentNumber = currentNumber*10 + (c-'0')
            }

            if((!c.isDigit() && c!=' ') || index==s.length-1){
                when(lastOperator){
                    '+'-> stack.push(currentNumber)
                    '-'-> stack.push(-currentNumber)
                    '*'-> stack.push(stack.pop() * currentNumber)
                    '/'-> stack.push(stack.pop() / currentNumber)
                }
                lastOperator = c
                currentNumber = 0
            }
        }

        var sum = 0
        while (stack.isNotEmpty()) sum+=stack.pop()
        return sum

    }
}