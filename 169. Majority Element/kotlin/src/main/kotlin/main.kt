/*
169. Majority Element(Easy)
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:

n == nums.length
1 <= n <= 5 * 104
-231 <= nums[i] <= 231 - 1

Follow-up: Could you solve the problem in linear time and in O(1) space?

*/

/*
直觀算法：用hashmap計算 然後得出majority
屌炸天算法：moore voting algo.
核心觀念是說：同時對 當選者 和 落選者 減去相同票數，結果不變。
套用在題目上，假設取兩個不同的數字刪掉，剛好取到兩個落選者 ->結果不變
假設剛好取到一個當選者一個落選者，結果也是不變
結論就是：我拿一對不同的數字從隊伍裡刪掉 結果一樣不變

實際上作法就是 用一個佔存器&計數器 佔存器tmp初始化為nums[0] 計數器cnt=1，代表nums[0]這個元素出現過一次
然後loop過每一個元素，如果下一個元素和tmp相同，cnt++，不同就cnt--。
如果同時不同 又cnt=0，代表前面的數字全部都是兩兩一組不相同的配對，全部刪掉，從這個元素開始重新算

 */
class Solution {
    fun majorityElement(nums: IntArray): Int {
        var tmp = nums[0]
        var cnt = 1
        for(i in 1 until nums.size){
            if(tmp == nums[i]) ++cnt
            else{
                if(cnt==0){
                    tmp = nums[i]
                    ++cnt
                }else
                    --cnt
            }
        }

        return tmp
    }
}

fun main(args: Array<String>) {
    println("Hello World!")
}