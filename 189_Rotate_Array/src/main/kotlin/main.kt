import java.util.*

/*
189. Rotate Array(Medium)
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:

1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5


Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?

 */

class Solution {
    fun rotate(nums: IntArray, k: Int): Unit {
        val k = k % nums.size

        repeat(k){
            val tmp = nums[nums.size-1]
            for(i in nums.size-1 downTo 1){
                nums[i] = nums[i-1]
            }
            nums[0] = tmp
        }
    }

    fun rotateV2(nums: IntArray, k: Int): Unit {
        val k = k % nums.size
        val queue:Queue<Int> = LinkedList()
        for(i in nums.size-k .. nums.size-1){
            queue.offer(nums[i])
        }

        for(i in nums.size-1 downTo k){
            nums[i] = nums[i-k]
        }

        for(i in 0 .. k-1){
            nums[i] = queue.poll()
        }
    }

    fun rotateV3(nums: IntArray, k: Int): Unit {
        val k = k % nums.size
        val subArray = nums.sliceArray(nums.size-k .. nums.size-1)
        for(i in nums.size-1 downTo k){
            nums[i] = nums[i-k]
        }

        for(i in 0 .. k-1){
            nums[i] = subArray[i]
        }
    }

    fun rotateV4(nums: IntArray, k: Int): Unit {
        val k = k % nums.size

        nums.reverse()
        reverse(nums,0,k-1)
        reverse(nums,k,nums.size-1)
    }

    fun reverse(nums: IntArray,start:Int,end:Int){
        var head = start
        var tail = end

        while (head<tail){
            val tmp = nums[head]
            nums[head] = nums[tail]
            nums[tail] = tmp

            ++head
            --tail
        }
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(1,2,3,4,5,6,7,8)
    println(input.contentToString())
    val k = 3
    val result = Solution().rotateV4(input,k)
    println(input.contentToString())
}