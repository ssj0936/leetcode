/*
75. Sort Colors

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun sortColors(nums: IntArray)
}

class Solution:Sol {
    override fun sortColors(nums: IntArray): Unit {
        var head = 0
        var tail = nums.lastIndex
        var index = 0

        while(index <= tail){
            when(nums[index]){
                0->{
                    swap(nums, index, head)
                    ++head
                    ++index
                }
                1->{
                    ++index
                }
                2->{
                    swap(nums, index, tail)
                    --tail
                }
            }
        }
    }

    private fun swap(nums: IntArray, indexA:Int, indexB:Int){
        if(indexA == indexB) return

        val tmp = nums[indexA]
        nums[indexA] = nums[indexB]
        nums[indexB] = tmp
    }
}