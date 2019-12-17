// Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

// Example:

// Input:  [1,2,3,4]
// Output: [24,12,8,6]
// Note: Please solve it without division and in O(n).

// Follow up:
// Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
      var left = IntArray(nums.size)
      var right = IntArray(nums.size)
      for(i in left.indices){
        if(i == 0)
          left[i] = 1
        else
          left[i] = left[i-1] * nums[i-1]
      }

      var accumlator = 1;
      for(i in right.size-1 downTo 0){
        right[i] = left[i]*accumlator
        accumlator *= nums[i]
      }
      return right
    }
}

fun main() {
  var nums:IntArray = intArrayOf(1,2,3,4)
  var s = Solution()
  var result = s.productExceptSelf(nums)
  println(result.contentToString())
}