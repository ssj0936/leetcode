/*
152. Maximum Product Subarray(Medium)
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.
A subarray is a contiguous subsequence of the array.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


Constraints:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

 */
interface Sol{
    fun maxProduct(nums: IntArray): Int
}

class Solution :Sol{
    /*
    DP解，兩個陣列
    dpMax[i]代表含第i位的最大乘積
    dpMin[i]代表含第i位的最小乘積

    所以每一回合 就是dpMax[i-1]*nums[i], dpMin[i-1]*nums[i], nums[i]在做比較

    其實也可以把陣列拿掉 改用變數就好

     */
    override fun maxProduct(nums: IntArray): Int {
        if(nums.size == 1) return nums[0]
        var record = nums[0]
        var dpMax = record
        var dpMin = record
        for(index in 1 until nums.size){
            val newDpMax = maxOf(dpMax*nums[index],dpMin*nums[index],nums[index])
            val newDpMin = minOf(dpMax*nums[index],dpMin*nums[index],nums[index])

            dpMax = newDpMax
            dpMin = newDpMin

            if(dpMax>record) record = dpMax
        }
        return record
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}