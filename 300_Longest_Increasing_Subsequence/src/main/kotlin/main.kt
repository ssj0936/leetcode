/*
300. Longest Increasing Subsequence(Medium)(cheat)

Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-104 <= nums[i] <= 104

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        //解法1
        //DP解，生成nums.size的array，result[i]代表以nums[i]為結尾的LIS長度
        //所以每一回合就是拿自己往前比，找出之前比自己小的數字(群A)，並在群A中找出result值最大的+1，成為自己的result值
        val result = IntArray(nums.size){1}

        for(i in nums.indices){
            for(j in 0 until i){
                if(nums[i]>nums[j] && result[j]>=result[i])
                    result[i] = result[j]+1
            }
        }
        return result.maxOrNull() ?: 0
    }

    fun lengthOfLIS_(nums: IntArray): Int {
        //解法1
        //DP解，一個array result，result[i]代表長度為i的LIS的最佳解的尾數
        //所謂最佳解舉個例子：[1,5,9],[11,55,99]，兩組比起來[1,5,9]會比較是最佳解，因為比較有機會往後接
        //loop過nums，每一回合就在result裡面search，找出第一個比自己大的值 並把她置換掉(代表先前長度為i的最佳解 比我還大，代表我可以置換他成為最佳解)
        //若找不到最佳解，就代表可以繼續往後接，那就push進result的尾端
        //最後答案即為 result.size(代表他最長可以接到i)

        //又由於result是排列好的值，所以可以用binary search來找出第一個比自己大的值
        val result = mutableListOf<Int>()

        for(i in nums){
            if(result.isEmpty()){
                result.add(i)
                continue
            }

            if (i > result[result.size-1]){
                result.add(i)
                continue
            }

            //kotlin binary search會index，如果沒有在裡面，會回傳inverted insertion point(-insertion point - 1)
            //insertion point的定義是本應該插入的index
            result.binarySearch(i,0,result.size-1).run {
                if(this<0){
                    //轉換回來
                    (this+1)*-1
                }else
                    this
            }.apply {
                result[this] = i
            }
//            findFirstBiggerThan(result,0,result.size-1, i).apply {
//                if(this != -1) result[this] = i
//            }
//            println(result)

        }
        return result.size
    }

    private fun findFirstBiggerThan(list:List<Int>, startIndex:Int, endIndex:Int, num:Int):Int{
        //表示只有一個
        if((endIndex - startIndex + 1) == 1) return startIndex

        val halfIndex = (endIndex - startIndex + 1)/2
    println("$list")
    println("startIndex:$startIndex / ${list[startIndex]}")
    println("endIndex:$endIndex / ${list[endIndex]}")
    println("halfIndex:$halfIndex / ${list[halfIndex]}")

        if(list[halfIndex] == num)
            return halfIndex
        //剩兩個
        if(halfIndex == endIndex || halfIndex == startIndex){
            return if(list[endIndex] == num || list[startIndex] == num) -1
            else if(list[startIndex] > num) startIndex
            else endIndex
        }
        else if(list[halfIndex] < num){
            return findFirstBiggerThan(list,halfIndex+1,endIndex,num)
        }else{
            return findFirstBiggerThan(list,startIndex,halfIndex,num)
        }
    }
}



fun main(args: Array<String>) {
//    [0,1,0,3,2,3]
//    val array = intArrayOf(4,10,4,3,8,9)
    val array = intArrayOf(0,1,0,3,2,3)
    val result = Solution().lengthOfLIS_(array)
    println(result)
}