/*
84. Largest Rectangle in Histogram
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.



Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4

Constraints:

1 <= heights.length <= 10^5
0 <= heights[i] <= 10^4
 */


interface Sol {
    fun largestRectangleArea(heights: IntArray): Int
}

class Solution:Sol {
    override fun largestRectangleArea(heights: IntArray): Int {
        var maxRectangleSize = Int.MIN_VALUE
        for(i in heights.indices){
            val leftSideLength = heights[i]
            var minHeightInProcess = leftSideLength
            var minHeightInProcessIndex = -1
            for(j in i .. heights.lastIndex){
                val rightSideLength = heights[j]

                if(rightSideLength < minHeightInProcess){
                    minHeightInProcess = rightSideLength
                    minHeightInProcessIndex = j
                }

                val min = Math.min(leftSideLength, minHeightInProcess)
                val width = j-i+1
                val area = min * width

                if(area>maxRectangleSize) {
                    println("i:$i($leftSideLength), j:$j($rightSideLength), minHeightInProcess:$minHeightInProcess($minHeightInProcessIndex) area:$area")
                    maxRectangleSize = area
                }
            }
        }

        return maxRectangleSize
    }
}

class SolutionStack:Sol {
    override fun largestRectangleArea(heights: IntArray): Int {
        val stack = ArrayDeque<Int>()
        val newHeights = heights.toMutableList().apply {
            add(0,0)
            add(this.size,0)
        }

        stack.push(0)

        var maxArea = 0
        for (index in 1 .. newHeights.lastIndex){
            println("index:$index, height:${newHeights[index]}")
            val thisValue = newHeights[index]
            //空的 或是stacktop比自己小，直接push
            if(stack.isEmpty() || newHeights[stack.peek()!!] < thisValue){
                println("push")
                stack.push(index)
            }else{
                //如果沒有比自己小
                //那就把前面的一個一個pop出來，把自己的index與前前一位的index 算出width，再乘上前一位的height
                while (newHeights[stack.peek()!!]>thisValue){
                    val heightIndex = stack.poll()
                    val stackTopHeight = newHeights[heightIndex]
                    val rectangleLeftBound = stack.peek()?:0
                    val area = stackTopHeight * (index-rectangleLeftBound-1)
                    println("heightIndex:$heightIndex, stackTopHeight:$stackTopHeight, rectangleLeftBound:$rectangleLeftBound, area:$area")
                    maxArea = Math.max(maxArea, area)
                }
                stack.push(index)
            }
            println(stack)
        }
        return maxArea
    }

    private fun <T> ArrayDeque<T>.push(el:T) = this.addLast(el)
    private fun <T> ArrayDeque<T>.poll():T = this.removeLast()
    private fun <T> ArrayDeque<T>.peek():T? = this.lastOrNull()
}
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}