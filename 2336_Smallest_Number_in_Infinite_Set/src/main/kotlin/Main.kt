import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}
class SmallestInfiniteSet() {
    private val minHeap = PriorityQueue<Int>()
    private val maxHeap = PriorityQueue<Int>(compareBy { -it })

    fun popSmallest(): Int {
        var value = -1
        if(minHeap.isNotEmpty())
            value = minHeap.remove()
        else if(maxHeap.size == 0)
            value = 1
        else
            value = maxHeap.peek()+1

        maxHeap.add(value)
        return value
    }

    fun addBack(num: Int) {
        if(maxHeap.contains(num)){
            maxHeap.remove(num)
            minHeap.add(num)
        }
    }
}

class SmallestInfiniteSet2() {
    private var current = 0
    private var sSet= PriorityQueue<Int>()
    fun popSmallest(): Int {
        return if (sSet.isNotEmpty()) {
            sSet.remove()
        } else ++current
    }

    fun addBack(num: Int) {
        if (current >= num && !sSet.contains(num)) sSet.add(num)
    }
}


/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */