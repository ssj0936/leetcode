/*
33. Search in Rotated Sorted Array(Medium)
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array
is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 */

interface Solu{
    fun solu(nums: IntArray, target: Int): Int
}

class Solution:Solu {
    /*
    最初的構想：

    就是從中間分開，如果一刀剛好切到目標就return
    否則，判斷起點終點中點
    三點之中是target就return
    如果三點成升冪排列 代表可以直接做BS
    如果三點切前後兩半 在區分大小升降冪，總共有四種排列組合：
    1.大 -> 小 和 小 -> 大，代表前半段是亂的，可以先檢查大小有沒有落在後半段，有就坐BS 沒有就把range往前半段縮小
    2.大 -> 小 和 大 -> 小，這不可能出現
    3.小 -> 大 和 小 -> 大，這剛剛前面判斷過了，已經濾掉了(直接做BS)
    4.小 -> 大 和 大 -> 小，代表後半段是亂的，可以先檢查大小有沒有落在前半段，有就坐BS 沒有就把range往後半段縮小
     */

    fun search(nums: IntArray, target: Int): Int {
        if(nums.size==1){
            return if(nums[0]==target) 0 else -1
        }

        var start = 0
        var end = nums.lastIndex
        while (start<end){
            val mid = (start+end)/2
            println("start:$start, mid:$mid, end$end")
            if(nums[mid]==target) return mid
            else if(nums[start]==target) return start
            else if(nums[end]==target) return end
            //小->大 小->大 直接做BS
            else if(nums[start]<=nums[mid] && nums[mid]<=nums[end]) {
                return bs(nums, start, end, target)
            }else if(nums[start]>nums[mid]) {//前半段亂掉 先檢查大小有沒有落在後半段 有BS 沒有就往前找
                if(nums[mid]<target && target<nums[end])
                    return bs(nums,mid,end, target)
                else
                    end = mid-1
            }else if(nums[mid]>nums[end]){//後半段亂掉 先檢查大小有沒有落在前半段 有BS 沒有就往後找
                if(nums[start]<target && target<nums[mid])
                    return bs(nums, start, mid, target)
                else
                    start = mid+1
            }
        }
        return -1
    }

    fun bs(nums: IntArray,start:Int, end:Int,target: Int):Int{
        var s = start
        var e = end
        while (s<=e){
            val mid = (s+e)/2

            if(nums[mid] == target) return mid
            else if(target < nums[mid]) e = mid-1
            else s = mid+1
        }
        return -1
    }

    /*
    就是把初始的想法更加縮減
    基本上都是用BS的方式在做

    找出正常排列的一邊，判斷target有沒有在裡面，有就往裡面做BS，沒有就往另一半繼續BS
     */
    fun searchV2(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.lastIndex
        while (start<=end){
            val mid = (start+end)/2

            if(nums[mid] == target)
                return mid
            //表示左邊是正常的，右邊是亂掉的
            else if(nums[start]<=nums[mid]){
                //start<target<mid
                if(nums[start]<=target && target<=nums[mid])
                    end = mid
                else
                    start = mid+1
            }else{
                if(nums[mid]<=target && target<=nums[end])
                    start = mid
                else
                    end = mid -1
            }
        }
        return -1
    }

    override fun solu(nums: IntArray, target: Int) = searchV2(nums,target)
}
fun main(args: Array<String>) {
    println("Hello World!")
}