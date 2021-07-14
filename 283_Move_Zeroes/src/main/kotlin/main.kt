/*
283. Move Zeroes(Easy)
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 10^4
-2^31 <= nums[i] <= 2^31 - 1

Follow up: Could you minimize the total number of operations done?

 */

class Solution {
    //直觀解法
    fun moveZeroes(nums: IntArray): Unit {
        if(nums.size == 1) return

        //指著頭和尾
        var head = 0
        var tail = nums.size-1

        //頭尾不重疊就持續運算
        while (head < tail){
            //如果head指向0 就從下一位開始loop到tail 往前shift一位
            if(nums[head] == 0){
                for(pointer in head+1 .. tail){
                    nums[pointer-1] = nums[pointer]
                }
                //tail補0 並且前進一位 指向下一個可以填0的位子
                nums[tail]=0
                --tail
            }

            //理應處理完head要前進，但考慮到下一位前挪的元素 如果也是0，那就要在處理一次
            //所以head不能動
            if(nums[head] != 0) ++head
        }
    }

    //使用累加器 紀錄目前要往前shift幾位
    fun moveZeroesV2(nums: IntArray): Unit {
        if(nums.size == 1) return

        //紀錄要從哪裡開始補0
        var tail = nums.size

        //acc紀錄目前遭遇幾個0，幾個0就代表之後的要往前挪幾位
        var acc = 0
        for(i in nums.indices){
            if(nums[i] == 0) {
                ++acc
                --tail
            }else{
                nums[i-acc] = nums[i]
            }
        }

        //補0
        for(i in tail until nums.size){
            nums[i] = 0
        }
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(0,1,0,3,12)
    println(input.contentToString())
    val result = Solution().moveZeroesV2(input)
    println(input.contentToString())
}