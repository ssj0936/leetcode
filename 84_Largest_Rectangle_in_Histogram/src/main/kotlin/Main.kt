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

class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
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
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}