/*
108. Convert Sorted Array to Binary Search Tree(Easy)
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.

Constraints:

1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in a strictly increasing order.
 */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/*
圖畫一畫就會想到了，balanced bst的話 root一定是中間的值，否則會有大機率不平衡
所以就是recursive的分兩半一直取中間值，
 */

class Solution {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if(nums.isEmpty()) return null
        return foo(nums,0, nums.size-1)
    }

    private fun foo(nums: IntArray, start:Int, end:Int):TreeNode?{
        if(start > end) return null

        val mid = (start+end)/2
        return TreeNode(nums[mid]).apply {
            left = foo(nums,start,mid-1)
            right = foo(nums, mid+1, end)
        }
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}