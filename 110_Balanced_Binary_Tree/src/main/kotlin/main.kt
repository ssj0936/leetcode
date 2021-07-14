import kotlin.math.max

/*
110. Balanced Binary Tree(Easy)
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104

 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Solution {
    fun isBalanced(root: TreeNode?): Boolean {
        if(root == null) return true
        else{
            if(!isBalanced(root.left)) return false
            else if(!isBalanced(root.right)) return false
            else{
//                if(root.left == null && root.right==null) {
//                    root.`val` = 1
//                }
//                else{
                    if(Math.abs((root.left?.`val` ?: 0) - (root.right?.`val` ?: 0)) > 1) return false
                    else{
                        root.`val` = max(root.left?.`val` ?: 0, root.right?.`val` ?: 0)+1
                        return true
                    }
//                }
            }
        }
    }

    fun getHeight(root: TreeNode?):Int{
        if(root == null) return 0
        else{
            if(root.left == null && root.right == null) return 1
            else return Math.max(getHeight(root.left), getHeight(root.right))+1
        }
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}