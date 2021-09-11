import java.util.*

/*
239. Sliding Window Maximum(Hard)
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the
array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one
position.
Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:
Input: nums = [1], k = 1
Output: [1]

Example 3:
Input: nums = [1,-1], k = 1
Output: [1,-1]

Example 4:
Input: nums = [9,11], k = 2
Output: [11]

Example 5:
Input: nums = [4,-2], k = 2
Output: [4]

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length

HINT:
How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
 */

class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k +1)
        for(i in result.indices){
            var max = Int.MIN_VALUE
            for(j in i until i+k){
                if(nums[j]>max) max = nums[j]
            }
            result[i] = max
        }
        return result
    }
    fun maxSlidingWindowDeque(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k +1)
        var recordIndex = 0
        val deque: Deque<Int> = ArrayDeque<Int>()

        for((index,value) in nums.withIndex()){

            while(!deque.isEmpty() && deque.peek()< index-k+1)
                deque.poll()

            while (!deque.isEmpty() && nums[deque.peekLast()] < value)
                deque.pollLast()

            deque.offer(index)
            if(index>=k-1){
                result[recordIndex++] = nums[deque.peek()]
            }
        }
        return result
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(1,3,-1,-3,5,3,6,7)
    val k = 3
    val result = Solution().maxSlidingWindowDeque(input,k)
    println(result.contentToString())
}