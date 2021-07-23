/*
410. Split Array Largest Sum(Hard)

Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Example 1:
Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Example 2:
Input: nums = [1,2,3,4,5], m = 2
Output: 9

Example 3:
Input: nums = [1,4,4], m = 3
Output: 4

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
 */

/*
小提示：用DP或binary search
跟1011差不多的題目，我也差不多都解不出來

dp[i][j] 代表，斷在index j的subArray，分成i組的subAnswer
dp[1][j] = nums.sum()
dp[i][j] = k遞迴從nums後面往前順，假定從k到末端為一組，所以0~k-1需要分i-1組，則可以從前面取答案
 */
class Solution {

    //DP解 幾乎是暴力解，O(mn^2)
    fun splitArray(nums: IntArray, m: Int): Int {
        val dp = Array(m+1){IntArray(nums.size){0} }

        for(i in 1 until m+1){
            for(j in nums.indices){
                //j+1個el分成i組，如果j+1數量比i小，一定是0
                if(i>j+1)
                    continue
                else if(i == 1) {
                    dp[i][j] = nums.sumFrom(0..j)
                }else{
                    var min = Int.MAX_VALUE
                    for(k in j downTo 1){
                        if(k < i-1) break

                        val fixGroupSum = nums.sumFrom(k .. j )
                        val candidate = dp[i-1][k-1]
                        min = Math.min(min,Math.max(fixGroupSum,candidate))
                    }
                    dp[i][j] = min
                }


            }
        }

        for(i in dp.indices){
            println(dp[i].contentToString())
        }
        return dp[m][nums.size-1]
    }

    private fun IntArray.sumFrom(range: IntRange):Int{
        var sum = 0
        for(i in range){
            sum +=this[i]
        }
        return sum
    }

    /*
    bs解，答案會有個上下限，最小值就是分成一個一堆，所以min是 nums最大值(分成nums.size堆，也就是一個一堆)
    上線就是全部分一堆，也就是max則會是nums.sum(分成1堆)
    答案就會在上下限之中，而且他也排列好了，我們只需要找出來(BS最快)，找出最小符合題目需求，也就是分成m堆 而又是最小的值

    i= min, j=max
    如果(i+j)/2=mid 可以分成k堆，則有三種可能：k == m, k<m, k>m

    if(k>m) 表示分得太多堆了，表示目前這個mid值太小(所以才會分超過m堆) 需要往上找 i=mid+1
    if(k<m) 表示太大了，表示目前mid值太大了需要往回找，需要往回找j=mid
    if(k == m) 再繼續往下找因為有可能有更小的解j=mid
     */
    fun splitArrayV2(nums: IntArray, m: Int): Int {
        val min = nums.max()?:0
        val max = nums.sum()

        var i = min
        var j = max
        while (i<j){
            val mid = (i+j)/2
            val group = getMinGroupCount(nums,mid)

            if(group > m)i = mid+1
            else if(group == m) j = mid
            else j = mid-1
        }

        return i
    }
    fun getMinGroupCount(nums:IntArray, maxCapacity:Int):Int{
        var count = 1
        var sum = 0
        for(i in nums.indices){
            if((sum + nums[i])>maxCapacity){
                ++count
                sum = 0
            }
            sum += nums[i]
        }
        return count

    }

}

fun main(args: Array<String>) {
    val input = intArrayOf(0,8,1,4)
    val m = 4
    val result = Solution().splitArrayV2(input,m)
    println(result)
}