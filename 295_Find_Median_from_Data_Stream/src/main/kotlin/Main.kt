import java.util.*
import kotlin.math.min

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

class MedianFinderPriorityQueue{
    //poll拿出最大的
    private val heapSmallerPart = PriorityQueue<Int>{a, b -> b - a}
    //priority queue預設就是從最小開始拿
    private val heapBiggerPart = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if(heapBiggerPart.size ==0 || num > heapBiggerPart.peek()){
            heapBiggerPart.offer(num)
        }else
            heapSmallerPart.offer(num)

        //balanced
        if(heapBiggerPart.size - heapSmallerPart.size>1){
            heapSmallerPart.offer(heapBiggerPart.poll())
        }
        else if(heapSmallerPart.size - heapBiggerPart.size>1){
            heapBiggerPart.offer(heapSmallerPart.poll())
        }
    }

    fun findMedian(): Double {
        return if(heapBiggerPart.size > heapSmallerPart.size){
            heapBiggerPart.peek().toDouble()
        }else if(heapBiggerPart.size < heapSmallerPart.size){
            heapSmallerPart.peek().toDouble()
        }else{
            (heapSmallerPart.peek()+heapBiggerPart.peek()).toDouble()/2
        }
    }
}

class MedianFinder:Solution {
    //放數字比較大的那一半
    var minHeap = MinHeap()
    //放數字比較小的那一半
    var maxHeap = MaxHeap()

    override fun addNum(num: Int) {
        //第一個元素預設就是先塞minHeap
        //如果這個數字比"數字比較大的那一半"中 最小的一位 還要大，那就放入"數字比較大的那一半"
        if(minHeap.getSize()==0 || num > minHeap.peek()){
            minHeap.add(num)
        }
        //反之 如果這個數字比"數字比較小的那一半"中 最大的一位 還要小，那就放入"數字比較小的那一半"
        else
            maxHeap.add(num)

        //兩個heap要平衡高度，如果兩邊數量差超過1
        while(Math.abs(minHeap.getSize() - maxHeap.getSize())>1){
            if (minHeap.getSize() > maxHeap.getSize())
                maxHeap.add(minHeap.poll())
            else
                minHeap.add(maxHeap.poll())
        }
    }

    override fun findMedian(): Double {
        //兩個heap數量一樣多，代表data總數是偶數，回傳最中間的兩位平均和
        return if(minHeap.getSize() == maxHeap.getSize())
            (minHeap.peek() + maxHeap.peek()).toDouble()/2
        //比較多的那一方poll出來
        else if(minHeap.getSize() > maxHeap.getSize())
            minHeap.peek().toDouble()
        else
            maxHeap.peek().toDouble()
    }

    abstract class Heap{
        val heap: MutableList<Int> = mutableListOf()
        //預設是把最後一位調整好
        abstract fun heapfy()
        abstract fun poll():Int

        fun print(){
            println(heap)
        }

        fun getSize():Int = heap.size

        fun getParentIndex(index:Int): Int {
            return if (((index+1)/2-1) in 0..heap.lastIndex) (index+1)/2-1 else -1
        }

        fun getLeftChildIndex(index:Int):Int{
            return if((index+1)*2-1 in 0..heap.lastIndex) (index+1)*2-1 else -1
        }

        fun getRightChildIndex(index:Int):Int{
            return if((index+1)*2 in 0..heap.lastIndex) (index+1)*2 else -1
        }

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
            while (getParentIndex(index)!=-1 && heap[getParentIndex(index)] > num){
                swap(heap, getParentIndex(index), index)
                index = getParentIndex(index)
            }
        }

        //排出首位，把尾端元素塞入[0]，再從[0]開始向下調整
        override fun poll(): Int {
            val value:Int = heap[0]
            heap[0] = heap[heap.lastIndex]
            heap.removeAt(heap.lastIndex)

            var index = 0
            while (true){
                val leftChildIndex = getLeftChildIndex(index)
                val rightChildIndex = getRightChildIndex(index)
                    //兩子建在
                    if(leftChildIndex!=-1 && rightChildIndex!=-1){
                        //注意這個小於等於，給一個例子：[66, 29, 29]，如果沒有這個小於等於 結果不會正確
                        if(heap[leftChildIndex] <= heap[rightChildIndex] && heap[leftChildIndex] < heap[index]){
                            swap(heap, leftChildIndex, index)
                            index = leftChildIndex
                        }else if(heap[rightChildIndex] < heap[leftChildIndex] && heap[rightChildIndex] < heap[index]){
                            swap(heap, rightChildIndex, index)
                            index = rightChildIndex
                        }else{
                            break
                        }
                    }
                    //剩右子
                    else if(leftChildIndex==-1 && rightChildIndex!=-1){
                        if(heap[rightChildIndex] < heap[index]){
                            swap(heap, rightChildIndex, index)
                            index = rightChildIndex
                        }else
                            break
                    }
                    //剩左子
                    else if(leftChildIndex!=-1 && rightChildIndex==-1){
                        if(heap[leftChildIndex] < heap[index]){
                            swap(heap, leftChildIndex, index)
                            index = leftChildIndex
                        }else
                            break
                    }
                    else{
                        break
                    }
            }
            return value
        }
    }

    class MaxHeap:Heap(){

        override fun heapfy() {
            var index = heap.lastIndex
            val num = heap.last()
            while (getParentIndex(index)!=-1 && heap[getParentIndex(index)] < num){
                swap(heap, getParentIndex(index), index)
                index = getParentIndex(index)
            }
        }

        override fun poll(): Int {
            val value = heap[0]
            heap[0] = heap.last()
            heap.removeAt(heap.lastIndex)

            var index = 0
            while (true){
                val leftChildIndex = getLeftChildIndex(index)
                val rightChildIndex = getRightChildIndex(index)
                //兩子建在
                if(leftChildIndex!=-1 && rightChildIndex!=-1){
                    if(heap[leftChildIndex] >= heap[rightChildIndex] && heap[leftChildIndex] > heap[index]){
                        swap(heap, leftChildIndex, index)
                        index = leftChildIndex
                    }else if(heap[rightChildIndex] > heap[leftChildIndex] && heap[rightChildIndex] > heap[index]){
                        swap(heap, rightChildIndex, index)
                        index = rightChildIndex
                    }else{
                        break
                    }
                }
                //剩右子
                else if(leftChildIndex==-1 && rightChildIndex!=-1){
                    if(heap[rightChildIndex] > heap[index]){
                        swap(heap, rightChildIndex, index)
                        index = rightChildIndex
                    }else
                        break
                }
                //剩左子
                else if(leftChildIndex!=-1 && rightChildIndex==-1){
                    if(heap[leftChildIndex] > heap[index]){
                        swap(heap, leftChildIndex, index)
                        index = leftChildIndex
                    }else
                        break
                }
                else{
                    break
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