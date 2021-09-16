/*
55. Jump Game(Medium)
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5

 */
interface Sol{
    fun sol(nums: IntArray): Boolean
}

class Solution :Sol{
    fun canJump(nums: IntArray): Boolean {
        if(nums.size>1 && nums[0]==0) return false

        var prevNum = -1
        var zeroCount = 0
        for(i in nums.lastIndex-1 downTo 0){
            //開始進入0的領域
            if(prevNum!=0 && nums[i]==0)
                ++zeroCount
            //正在0的領域
            else if(prevNum==0 && nums[i]==0)
                ++zeroCount
            //正踏出0的領域
            else if(prevNum==0 && nums[i]!=0){
                if(nums[i]<=zeroCount) return false
                zeroCount=0
            }
            //在0的領域外
            else if(prevNum!=0 && nums[i]!=0){

            }
            prevNum = nums[i]
        }
        return true
    }

    var found = false
    private fun canJumpV2(nums: IntArray): Boolean {
        if(nums.size==1) return true

        dfs(nums,0)
        return found
    }

    private fun dfs(nums: IntArray, start:Int){
        if(found) return
        else if(start==nums.lastIndex)
            found = true
        else{
            if(nums[start]==0) return

            for(i in (if(start+nums[start]>nums.lastIndex) nums.lastIndex else start+nums[start]) downTo (if(start-1<0) 0 else start)){
                if(i == start) continue
                dfs(nums,i)
            }
        }
    }

    /*
    DP算法，dp[i]代表 以i為首的無效格子數
    所謂無效格子，代表踩進去就出不來的地方[3,2,1,0,4]，321的部分都是無效格子 因為跨不過0
    最後return是 起點是不是無效格子
     */
    private fun canJumpV3(nums: IntArray):Boolean{
        if(nums.size==1) return true
//        val dp = Array<Int>(nums.size){0}

        nums[nums.lastIndex] = 0

        for(i in nums.lastIndex-1 downTo 0){
            if(nums[i]==0)
                nums[i] = nums[i+1]+1
            else if(nums[i]<=nums[i+1])
                nums[i] = nums[i+1]+1
            else
                nums[i] = 0
        }

        return nums[0]==0
    }

    /*
    greedy算法，我們只需要知道從起點最遠可以走到哪，如果可以最遠有超過終點 那就代表true
    用一個reach代表最遠可達的距離，並且loop這個array，如果目前it超過了reach 代表我們已經走超過到目前最遠可達的地方了 return false
    如果reach中途已經超過lastIndex了，return true
     */
    private fun canJumpV4(nums: IntArray):Boolean{
        var reach = 0
        for(i in nums.indices){
            if(i>reach || reach>=nums.lastIndex) break

            reach = Math.max(reach, i+nums[i])
        }
        return reach>=nums.lastIndex
    }


        override fun sol(nums: IntArray) = canJumpV4(nums)
}

fun main(args: Array<String>) {
    println("Hello World!")
}