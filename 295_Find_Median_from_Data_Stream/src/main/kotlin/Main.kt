import java.util.*

/*
295. Find Median from Data Stream
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0


Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.


Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Solution{
    fun addNum(num: Int)
    fun findMedian(): Double
}

class MedianFinder:Solution {
    var minHeap = mutableListOf<Int>()
    var minHeapBoundary = -1
    var minHeapSize = minHeapBoundary -0 +1

    var maxHeap = mutableListOf<Int>()
    var maxHeapBoundary = -1
    var maxHeapSize = maxHeapBoundary -0 +1

    override fun addNum(num: Int) {

    }

    override fun findMedian(): Double {

    }

    interface Heap{
        var heap:MutableList<Int>
        fun heapfy()
        fun add(num:Int)
        fun peek():Int
        fun <T> swap(heap:MutableList<T>, indexA:Int, indexB:Int)
    }

    class MinHeap:Heap{
        override var heap: MutableList<Int> = mutableListOf()

        override fun heapfy() {
            var index = heap.lastIndex
            val num = heap.last()
            while (heap[(index+1)/2-1] < num){
                swap(heap, (index+1)/2-1, index)
                index = (index+1)/2-1
            }
        }

        override fun add(num: Int) {
            heap.add(num)
            if(heap.size==1)
                return
            heapfy()
        }

        override fun peek(): Int  = heap[0]

        override fun <T> swap(heap: MutableList<T>, indexA: Int, indexB: Int) {
            val tmp = heap[indexA]
            heap[indexA] = heap[indexB]
            heap[indexB] = tmp
        }
    }

    class MaxHeap:Heap{
        override var heap: MutableList<Int> = mutableListOf()

        override fun heapfy() {
            var index = heap.lastIndex
            val num = heap.last()
            while (heap[(index+1)/2-1] > num){
                swap(heap, (index+1)/2-1, index)
                index = (index+1)/2-1
            }
        }

        override fun add(num: Int) {
            heap.add(num)
            if(heap.size==1)
                return
            heapfy()
        }

        override fun peek(): Int  = heap[0]

        override fun <T> swap(heap: MutableList<T>, indexA: Int, indexB: Int) {
            val tmp = heap[indexA]
            heap[indexA] = heap[indexB]
            heap[indexB] = tmp
        }
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */