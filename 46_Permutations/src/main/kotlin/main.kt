/*
46. Permutations(Medium)
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.

 */
class Solution {

    private val result = mutableListOf<List<Int>>()
    fun permute(nums: IntArray): List<List<Int>> {
        foo(nums,-1)
        return result
    }

    private fun foo(arr:IntArray, startIndex:Int){
        if(startIndex == arr.lastIndex){
            result.add(arr.toList())
            return
        }

        val newStartIndex = startIndex+1
        for(index in newStartIndex until arr.size){
            val newArr = arr.clone()
            swap(newArr, newStartIndex, index)
            foo(newArr, newStartIndex)
        }
    }

    private fun swap(arr:IntArray, indexA:Int, indexB:Int){
        val tmp = arr[indexA]
        arr[indexA] = arr[indexB]
        arr[indexB] = tmp
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(1,2,3)
    val result = Solution().permute(input)
    println(result)
}