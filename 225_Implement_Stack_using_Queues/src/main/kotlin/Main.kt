import java.util.*

class MyStack() {
    private val queue = LinkedList<Int>()
    fun push(x: Int) {
        queue.offer(x)
    }

    fun pop(): Int {
        val size = queue.size
        repeat(size-1){
            queue.offer(queue.poll())
        }
        return queue.poll()
    }

    fun top(): Int = queue.peekLast()

    fun empty(): Boolean = queue.isEmpty()

}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */