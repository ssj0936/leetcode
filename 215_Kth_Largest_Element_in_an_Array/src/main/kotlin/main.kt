/*
215. Kth Largest Element in an Array(Medium)
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:

1 <= k <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4

 */

/*
思路是說，用quick sort的方法，如果pivot歸位後，那個index是k，那就回傳nums[pivot]
如果pivot比k小，那就要往回找；如果pivot比k大，那就往後找
 */
class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        return quickSelectV2(nums,0, nums.lastIndex, k-1)
    }

    fun swap(nums:IntArray, a:Int, b:Int){
        val tmp = nums[a]
        nums[a] = nums[b]
        nums[b] = tmp
    }

    //遞迴解1
    fun quickSelect(nums: IntArray, head:Int, tail:Int, k:Int):Int {
        if (nums.size == 1) return nums[head]

        val pivot = tail
        var i = head - 1
        var j = head
//        println("Before")
//        println("${nums.contentToString()}, pivot:$pivot")
        for (k in j until pivot) {
            if (nums[j] > nums[pivot]) {
                swap(nums, ++i, j)
            }
            ++j
        }


//        while (left<right){
//            while (nums[left]>nums[pivot] && left<right) ++left
//            while (nums[right]<nums[pivot] && left<right) --right
//
//            if(left!=right)
//                swap(nums, left, right)
//        }

        val swapPoint = i + 1
        swap(nums, swapPoint, pivot)
//        println("After")
//        println("${nums.contentToString()}, swapPoint:$swapPoint")

        if (swapPoint == k) return nums[swapPoint]
        else if (swapPoint > k) {
//            println("A:quickSelect(nums,$head,${swapPoint-1},k)-----")
            return quickSelect(nums, head, swapPoint - 1, k)
        } else {
//            println("B:quickSelect(nums,${swapPoint+1},$tail,k)-----")
            return quickSelect(nums, swapPoint + 1, tail, k)
        }
    }

    //遞迴解2
    fun quickSelectV2(nums: IntArray, head:Int, tail:Int, k:Int):Int{
        if(nums.size == 1) return nums[head]

        val pivot = tail
        var left = head
        var right = pivot-1
        println("Before")
        println("${nums.contentToString()}, pivot:$pivot")


        while (left<=right){
            while (left<=right && left<=pivot-1 && nums[left]>nums[pivot]) {
                ++left
                println("left:$left")
            }
            while (left<=right && right>=head && nums[right]<nums[pivot] ) {
                --right
                println("right:$right")
            }

            if(left<right) {
                println("AAA")
                swap(nums, left, right)
                ++left
                --right
            }else if(left==right)
                break
        }

        val swapPoint = right+1
        swap(nums,swapPoint, pivot)
        println("After")
        println("${nums.contentToString()}, swapPoint:$swapPoint")

        if(swapPoint == k) return nums[swapPoint]
        else if(swapPoint > k){
            println("A:quickSelect(nums,$head,${swapPoint-1},k)-----")
            return quickSelectV2(nums,head,swapPoint-1,k)
        }
        else{
            println("B:quickSelect(nums,${swapPoint+1},$tail,k)-----")
            return quickSelectV2(nums,swapPoint+1,tail,k)
        }
    }

    //迴圈解1
    fun findKthLargestV2(nums: IntArray, k: Int): Int {
        if(nums.size == 1) return nums[0]

        var index = -1
        var head = 0
        var tail = nums.lastIndex
        var m = k-1
//        println("Start")
//        println(nums.contentToString())
        do{
            index = partivtion(nums, head, tail, m)
//            println("${nums.contentToString()},index:$index")

            if (index == m) return nums[index]
            else if (index > m) {
                tail = index - 1
            } else {
                head = index + 1
            }
//            println("index:$index, head:$head, tail:$tail")

        }while (index != m)

        return -1
    }

    //return pivot的index
    fun partivtion(nums: IntArray, head:Int, tail:Int, k:Int):Int {
        if (head == tail) return head

        val pivot = tail
        var i = head - 1
        var j = head
        while (j<pivot) {
            if (nums[j] > nums[pivot]) {
                swap(nums, ++i, j)
            }
            ++j
        }

        val swapPoint = i + 1
        swap(nums, swapPoint, pivot)

        return swapPoint
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(3,2,1,5,6,4)
    val k = 2
    println(Solution().findKthLargestV2(input,k))
}