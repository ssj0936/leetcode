/*
34. Find First and Last Position of Element in Sorted Array(Medium)
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]

Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non-decreasing array.
-10^9 <= target <= 10^9
 */

class Solution {
    /*
    最一開始的想法：先做一次BS，沒找到就return -1,-1
    有找到就分兩半，左邊那半找頭，右邊那半找尾，等於是做3次BS O(3logN)
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val index = binarySearch(nums, target)
        if (index == -1) return intArrayOf(-1, -1)
        else {
            println(index)
            var indexResultStart = 0
            var indexResultEnd = nums.lastIndex

            if (index == 0 || nums[index - 1] != target)
                indexResultStart = index
            else {
                var start = 0
                var end = index - 1
                while (start <= end) {
                    val mid = (start + end) / 2
                    if (target == nums[mid]) {
                        if (mid == 0 || nums[mid - 1] != target) {
                            indexResultStart = mid
                            break
                        } else
                            end = mid - 1
                    } else if (target < nums[mid]) {
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                }
            }

            if (index == nums.lastIndex || nums[index + 1] != target)
                indexResultEnd = index
            else {
                var start = index + 1
                var end = nums.lastIndex
                while (start <= end) {
                    val mid = (start + end) / 2
                    if (target == nums[mid]) {
                        if (mid == nums.lastIndex || nums[mid + 1] != target) {
                            indexResultEnd = mid
                            break
                        } else
                            start = mid + 1
                    } else if (target < nums[mid]) {
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                }
            }
            return intArrayOf(indexResultStart, indexResultEnd)
        }
    }

    private fun binarySearch(array: IntArray, target: Int): Int {
        var start = 0
        var end = array.lastIndex
        while (start <= end) {
            var mid = (start + end) / 2
            if (target == array[mid]) return mid
            else if (target < array[mid]) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return -1
    }

    /*
    做兩次BS 一次找最頭 一次找最尾
    只需要做兩次O(2lonN)
     */
    fun searchRangeV2(nums: IntArray, target: Int): IntArray {
        if(nums.isEmpty()) return intArrayOf(-1, -1)
        //find first index first
        var indexResultStart = -1
        var indexResultEnd = indexResultStart

        var start = 0
        var end = nums.lastIndex
        var upperBound = nums.lastIndex
        var lowerBound = 0
        while (start<=end){
            val mid = (start + end) / 2
            if (target == nums[mid]) {
                indexResultStart = mid

                end = mid - 1
                lowerBound = mid - 1

            } else if (target < nums[mid]) {
                end = mid - 1
                upperBound = mid
            } else {
                start = mid + 1
                lowerBound = mid +1
            }
        }
        if(indexResultStart == -1) return intArrayOf(-1, -1)

        start = lowerBound
        end = upperBound
        while (start <= end) {
            val mid = (start + end) / 2
            if (target == nums[mid]) {
                if (mid == nums.lastIndex || nums[mid + 1] != target) {
                    indexResultEnd = mid
                    break
                } else
                    start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return intArrayOf(indexResultStart, indexResultEnd)
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(5,7,7,8,8,10)
    val result = Solution().searchRangeV2(input,8)
    println(result.contentToString())
}