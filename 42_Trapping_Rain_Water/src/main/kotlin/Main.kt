/*
42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun trap(height: IntArray): Int
}

class Solution:Sol {
    override fun trap(height: IntArray): Int {
        var maxRight = 0
        val arrayMaxRight = Array<Int>(height.size){0}
        for(i in height.lastIndex downTo 0){
            arrayMaxRight[i] = maxRight
            maxRight = Math.max(height[i], maxRight)
        }

        var maxLeft = 0
        val arrayMaxLeft = Array<Int>(height.size){0}
        for(i in 0 .. height.lastIndex){
            arrayMaxLeft[i] = maxLeft
            maxLeft = Math.max(height[i], maxLeft)
        }

        var count = 0
        for(i in height.indices){
            var tmp = 0
            if(height[i]<arrayMaxRight[i] && height[i]<arrayMaxLeft[i])
                tmp = Math.min(arrayMaxRight[i], arrayMaxLeft[i]) - height[i]

            count +=tmp
        }

        return count
    }
}

class Solution2:Sol {
    override fun trap(height: IntArray): Int {
        var maxRight = 0
        val arrayMaxRight = Array<Int>(height.size){0}
        for(i in height.lastIndex downTo 0){
            arrayMaxRight[i] = maxRight
            if(height[i] > maxRight)
                maxRight = height[i]
        }

        var maxLeft = 0
        var count = 0
        for(i in height.indices){
            var tmp = 0
            if(height[i]<arrayMaxRight[i] && height[i]<maxLeft)
                tmp = Math.min(arrayMaxRight[i], maxLeft) - height[i]

            count +=tmp

            maxLeft = Math.max(height[i], maxLeft)
        }

        return count
    }
}