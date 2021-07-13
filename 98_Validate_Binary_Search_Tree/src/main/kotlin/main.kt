/*
98. Validate Binary Search Tree(Medium)

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:

The number of nodes in the tree is in the range [1, 104].
-2^31 <= Node.val <= 2^31 - 1

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
    //直觀的想法：
    //valid代表：1.左邊是valid 2.右邊是valid 3.root比左邊每一點都大 且 比右邊每一點都小
    //關於第3點，又因為1和2兩點，可以濃縮為：root比左邊最大點大 且 比右邊最小點小
    fun isValidBST(root: TreeNode?): Boolean {
        if(root == null) return true
        else if(root.left == null && root.right==null) return true

        if(root?.left !=null){
            var pointer:TreeNode? = root.left
            while (pointer?.right!=null)
                pointer = pointer.right
            if(pointer?.`val`!! > root.`val`) return false
        }

        if(root?.right !=null){
            var pointer:TreeNode? = root.right
            while (pointer?.left!=null)
                pointer = pointer.left
            if(pointer?.`val`!! < root.`val`) return false
        }

        return isValidBST(root?.left) && isValidBST(root?.right)
    }

    //可以想像成：把bst依照左中右的順序排出來，然後從頭掃一遍一定是要increasing排列
    var record:Int? = null
    fun isValidBSTV2(root: TreeNode?): Boolean {
        if(root==null) return true
        else if(!isValidBSTV2(root.left)) return false
        else if(record!=null && record!! >= root.`val`) return false
        record = root.`val`
        return isValidBST(root.right)
    }

}
fun main(args: Array<String>) {
    println("Hello World!")
}