/*
26. Remove Duplicates from Sorted Array(Easy)

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
Return k after placing the final result in the first k slots of nums.
Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Constraints:
0 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
 */
class Solution {
    fun removeDuplicates(nums: IntArray): Int {

        var index = 0
        //從第二個開始算
        for(i in 1 until nums.size){
            //只要和前一個不同 就觸發條件
            if(nums[i] != nums[i-1]){
                nums[++index] = nums[i]
            }
        }

        return index+1
    }
}
fun main(args: Array<String>) {
    var input = intArrayOf(0,0,1,1,1,2,2,3,3,4)
    val result = Solution().removeDuplicates(input)
    println(result)
    println(input.contentToString())
}