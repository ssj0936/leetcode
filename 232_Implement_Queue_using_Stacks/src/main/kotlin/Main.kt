import java.util.ArrayDeque

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class MyQueue() {
    private val stackForPush = ArrayDeque<Int>()
    private val stackForPop = ArrayDeque<Int>()
    private var count = 0

    fun push(x: Int) {
        while (stackForPop.isNotEmpty()){
            stackForPush.push(stackForPop.pop())
        }
        ++count
        stackForPush.push(x)
    }

    fun pop(): Int {
        while (stackForPush.isNotEmpty()){
            stackForPop.push(stackForPush.pop())
        }
        --count
        return stackForPop.pop()
    }

    fun peek(): Int {
        while (stackForPush.isNotEmpty()){
            stackForPop.push(stackForPush.pop())
        }
        return stackForPop.peek()
    }

    fun empty(): Boolean {
        return count==0
    }
}

class MyQueue() {
    private val stackForPush = ArrayDeque<Int>()
    private val stackForPop = ArrayDeque<Int>()
    private var count = 0

    fun push(x: Int) {
        stackForPush.push(x)
    }

    fun pop(): Int {
        if(stackForPop.isEmpty())
            while (stackForPush.isNotEmpty()){
                stackForPop.push(stackForPush.pop())
            }

        return stackForPop.pop()
    }

    fun peek(): Int {
        if(stackForPop.isEmpty())
            while (stackForPush.isNotEmpty()){
                stackForPop.push(stackForPush.pop())
            }
        return stackForPop.peek()
    }

    fun empty(): Boolean {
        return stackForPop.size==0 && stackForPush.size==0
    }
}