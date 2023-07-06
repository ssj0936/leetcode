import java.util.*

class Solution {
    fun calculate(s: String): Int {
        val stack = LinkedList<String>()

        var i = 0
        while (i<s.length){
            when (val char = s[i]) {
                ' ' -> {
                    ++i
                }
                '+', '-', '*', '/' -> {
                    stack.push(char.toString())
                    ++i
                }
                else -> {
                    val builder = StringBuilder().apply { append(s[i++]) }
                    while (i <s.length && s[i].isNumber()){
                        builder.append(s[i])
                        ++i
                    }

                    var number = builder.toString().toInt()

                    while (stack.peek() == "*" || stack.peek()=="/"){
                        val operator = stack.pop()
                        val anotherNumber = stack.pop().toInt()
                        number = if(operator=="*"){anotherNumber * number} else {anotherNumber/number}
                    }

                    stack.push(number.toString())
                }
            }
        }
        var base = stack.poll().toInt()
        while (stack.isNotEmpty()){
            val operator = stack.poll()
            val anotherNumber = stack.poll().toInt()
            base = if(operator=="+"){base + anotherNumber} else {base - anotherNumber}
        }

        return base
    }

    private fun Char.isNumber():Boolean = this in '0'..'9'
}