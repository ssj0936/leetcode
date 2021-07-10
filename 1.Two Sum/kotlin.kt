// Given an array of integers, return indices of the two numbers such that they add up to a specific target.

// You may assume that each input would have exactly one solution, and you may not use the same element twice.

// Example:

// Given nums = [2, 7, 11, 15], target = 9,

// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].


class Solution1 {
  fun twoSum(nums: IntArray, target: Int): IntArray? {
    for(i in 0..nums.size-1){
      for(j in i..nums.size-1){
        if(i == j) continue
        if(nums[i]+nums[j] == target){
          return intArrayOf(i,j)
        }
      }
    }
    return null
  }
}

fun main(args: Array<String>) {
  val nums = intArrayOf(2, 7, 11, 15)
  val target = 9
  val sol = Solution1()
  val result = sol.twoSum(nums,target)
  println(result?.contentToString())
}