/*
543. Diameter of Binary Tree(Easy)
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100

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

class Solution {
    var max = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        getHeight(root)
        return max
    }

    /*
    第一次腦衝寫法
    */
    private fun getHeight(root:TreeNode?):Int{
        if(root==null) return 0

        if(root.left == null && root.right==null) return 0

        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)
        val pathValue = leftHeight+rightHeight+(if(root.left == null) 0 else 1) +(if(root.right == null) 0 else 1)
        if(max < pathValue) max = pathValue

        return Math.max(leftHeight,rightHeight)+1
    }

    /*
    比較簡潔的寫法
     */
    private fun getHeightV2(root:TreeNode?):Int{
        if(root==null) return 0

        //不需要特別處理這個
        //if(root.left == null && root.right==null) return 0

        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)
        max = Math.max(max, leftHeight+rightHeight)
        //這邊+1 是把連到parent的那一條也加上去，反正不是最後要return的結果，可以髒一點
        return Math.max(leftHeight,rightHeight)+1
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}