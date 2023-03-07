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
    var minHeap = MinHeap()
    var maxHeap = MaxHeap()

    override fun addNum(num: Int) {
        if(minHeap.getSize()==0 || num > minHeap.peek()){
            minHeap.add(num)
        }else{
            maxHeap.add(num)
        }

        while(Math.abs(minHeap.getSize() - maxHeap.getSize())>1){
            if(minHeap.getSize()>maxHeap.getSize())
                maxHeap.add(minHeap.poll())
            else
                minHeap.add(maxHeap.poll())
        }
    }

    override fun findMedian(): Double {
        if(minHeap.getSize() == maxHeap.getSize())
            return (minHeap.peek()+maxHeap.peek()).toDouble()/2
        else if(minHeap.getSize()>maxHeap.getSize())
            return minHeap.peek().toDouble()
        else
            return maxHeap.peek().toDouble()
    }

    abstract class Heap(){
        var heap: MutableList<Int> = mutableListOf()
        fun getSize(): Int = heap.size

        abstract fun heapfy()
        abstract fun poll(): Int

        fun add(num: Int) {
            heap.add(num)
            if(heap.size==1)
                return
            heapfy()
        }

        fun peek(): Int  = heap[0]

        fun <T> swap(heap: MutableList<T>, indexA: Int, indexB: Int) {
            val tmp = heap[indexA]
            heap[indexA] = heap[indexB]
            heap[indexB] = tmp
        }
    }

    class MinHeap: Heap() {

        override fun heapfy() {
            var index = heap.lastIndex
            val num = heap.last()
            while (index>=0 && heap[(index+1)/2-1] < num){
                swap(heap, (index+1)/2-1, index)
                index = (index+1)/2-1
            }
        }

        override fun poll(): Int {
            val value = heap.first()
            heap[0] = heap[heap.lastIndex]
            heap.removeAt(heap.lastIndex)

            var pointer = 0
            while ((((pointer+1)*2-1)<heap.size && heap[(pointer+1)*2-1] < heap[pointer]) || (((pointer+1)*2)<heap.size && heap[(pointer+1)*2] < heap[pointer])){
                if(heap[(pointer+1)*2-1] < heap[pointer]) {
                    swap(heap, (pointer+1)*2-1, pointer)
                    pointer = (pointer + 1) * 2 - 1
                }else {
                    swap(heap, (pointer+1)*2, pointer)
                    pointer = (pointer + 1) * 2
                }
            }

            return value
        }
    }

    class MaxHeap: Heap() {
        override fun heapfy() {
            var index = heap.lastIndex
            val num = heap.last()
            while (index>=0 && heap[(index+1)/2-1] > num){
                swap(heap, (index+1)/2-1, index)
                index = (index+1)/2-1
            }
        }

        override fun poll(): Int {
            val value = heap.first()
            heap[0] = heap[heap.lastIndex]
            heap.removeAt(heap.lastIndex)

            var pointer = 0
            while ((((pointer+1)*2-1)<heap.size && heap[(pointer+1)*2-1] > heap[pointer]) || (((pointer+1)*2)<heap.size && heap[(pointer+1)*2] > heap[pointer])){
                if(heap[(pointer+1)*2-1] > heap[pointer]) {
                    swap(heap, (pointer+1)*2-1, pointer)
                    pointer = (pointer + 1) * 2 - 1
                }else {
                    swap(heap, (pointer+1)*2, pointer)
                    pointer = (pointer + 1) * 2
                }
            }

            return value
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */