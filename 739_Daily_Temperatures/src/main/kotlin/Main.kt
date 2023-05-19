import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        val result = IntArray(temperatures.size)
        for(i in temperatures.indices){
            val t = temperatures[i]
            while (stack.isNotEmpty() && t > temperatures[stack.last()]){
                val pop = stack.removeLast()
                result[pop] = i - pop
            }
            stack.addLast(i)
        }
        return result
    }
}

class SolutionStack {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = Stack<Int>()
        val result = IntArray(temperatures.size)
        for(i in temperatures.indices){
            val t = temperatures[i]
            while (stack.isNotEmpty() && t > temperatures[stack.peek()]){
                val pop = stack.pop()
                result[pop] = i - pop
            }
            stack.push(i)
        }
        return result
    }
}

class SolutionNoStack {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        var stackTop = -1
        val s = IntArray(temperatures.size)
        val result = IntArray(temperatures.size)
        for(i in temperatures.indices){
            val t = temperatures[i]
            while (stackTop>=0 && t > temperatures[s[stackTop]]){
                val pop = s[stackTop--]
                result[pop] = i - pop
            }
            s[++stackTop] = i
        }
        return result
    }
}