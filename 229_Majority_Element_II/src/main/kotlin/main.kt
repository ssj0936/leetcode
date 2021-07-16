/*
229. Majority Element II(Medium)
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
Follow-up: Could you solve the problem in linear time and in O(1) space?

Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]

Constraints:

1 <= nums.length <= 5 * 10^4
-10^9 <= nums[i] <= 10^9

 */

/*
follow up很明顯就是不准用hashmap了，繼續用初接提的moore voting algo

一樣，核心概念就是：當選者 和 落選者減去相同票數 結果不變
這題需要先思考最多有幾個當選者，從elements that appear more than ⌊ n/3 ⌋ times這個限制看起來，最多只會有兩個

ok 那就代表我同時刪除兩位不同的當選者和一位落選者，三位一組的組合，對結果是沒問題的(因為都是各減一票麻)
那如果我只刪除一位當選者 和 兩位不同的落選者呢？因為落選者的數量限制(假設兩位落選者的票數是各n/3) 結果也是一樣的
那刪除三位不同的落選者呢？那就更不用說了

所以這次用兩個佔存器 兩個計數器 實作

 */
class Solution {
    fun majorityElement(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

//        if(nums.size==1) result.add(nums[0])
//        else if(nums.size==2) result.add(nums[0],nums[1])
//        else {
            var tmp01:Int? = null
            var cnt01 = 0
            var tmp02:Int? = null
            var cnt02 = 0

            for(thisNumber in nums){
                if(thisNumber == tmp01) ++cnt01
                else if (thisNumber == tmp02) ++cnt02
                else {
                    if(tmp01==null || cnt01 == 0){
                        tmp01 = thisNumber
                        ++cnt01
                    }else if(tmp02==null || cnt02 == 0){
                        tmp02 = thisNumber
                        ++cnt02
                    }else{
                        --cnt01
                        --cnt02
                    }
                }
            }

            //不能和初階題一樣 直接回傳tmp01 tmp02
            //需要考慮 如果兩個當選人都是同一個的狀況
            //那其中1個佔存器 可能就是存到雜魚 所以需要再loop一次
            val threshold = nums.size/3
            cnt01 = 0
            cnt02 = 0

            for(num in nums){
                if(tmp01 == num) ++cnt01
                else if (tmp02 == num) ++cnt02
            }

            if (cnt01>threshold)
                result.add(tmp01!!)
            if (cnt02> threshold)
                result.add(tmp02!!)

//        }
        return result
    }
}

fun main(args: Array<String>) {
    println("Hello World!")
}