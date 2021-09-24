/*
41. First Missing Positive(Hard)

Given an unsorted integer array nums, return the smallest missing positive integer.
You must implement an algorithm that runs in O(n) time and uses constant extra space.

Example 1:
Input: nums = [1,2,0]
Output: 3

Example 2:
Input: nums = [3,4,-1,1]
Output: 2

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1

Constraints:

1 <= nums.length <= 5 * 10^5
-2^31 <= nums[i] <= 2^31 - 1

HINT:
1.Think about how you would solve the problem in non-constant space. Can you apply that logic to the existing space?
2.We don't care about duplicates or non-positive integers
3.Remember that O(2n) = O(n)
 */
interface SolutionInterface{
    fun solution(nums: IntArray): Int
}

class Solution :SolutionInterface{
    /*
    一開始的想法，依照hint1，我是想用另一個陣列。把原先陣列的值當作新陣列的index填入
    例如[3,4,-1,1]，num[0]=3，所以就把3放入新陣列的index=3的位置中，新陣列就會長這樣[x,1,x,3]
    先不論x是什麼，最後我們從頭順一遍新的陣列 找到第一個i!=nums[i]就會是答案了
     */

    fun firstMissingPositive(nums: IntArray): Int {
        //醜陋的special case處理
        if(nums.size==1)
            return if(nums[0]==1) 2 else 1

        //從頭往尾順
        for(i in nums.indices){

            /*
            這邊是以swap的方式進行
            如果內容物 小於等於0 -> 不用動，當作是無效元素
            如果內容物 大於等於nums.size -> 不用動，當作是無效元素

            nums[i]!=i 的 狀況是指像是如果內容物剛好等於index 那也不用做了
            nums[i] != nums[nums[i]] 的 狀況是指，如果要swap的對象跟自己是一樣的，也就是swap前後結果一樣，那也不用做(例如[1,1])
             */
            while(nums[i]>0 && nums[i]<nums.size && nums[i]!=i && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i])
            }
        }

        //注意是從1開始找
        for(i in 1 .. nums.lastIndex){
            if(i != nums[i]) return i
        }

        //如果找不到 去檢查nums[0]
        // 如果nums[0]是最後一位+1 代表答案應該還要再+1
        // 如果nums[0]不是最後一位+1 那就代表nums[0]應該是無效元素 不用管他 直接return 最後一位+1
        return if(nums[0]==nums.last()+1) nums[0]+1 else nums.last()+1
    }

    fun swap(nums: IntArray, indexA:Int, indexB: Int){
        val tmp = nums[indexA]
        nums[indexA] = nums[indexB]
        nums[indexB] = tmp
    }

    override fun solution(nums: IntArray) = firstMissingPositive(nums)
}

fun main(args: Array<String>) {
    println("Hello World!")
}