/*
111. Minimum Depth of Binary Tree(Easy)
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
*/


class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun minDepth(root: TreeNode?): Int {
        if(root == null) return 0
        else{
            if(root.left == null && root.right == null) return 1
            else{
                //小陷阱，如果某一邊的孩子是null 就不能用那一邊的min depth來算
                if(root.left == null)
                    return 1+minDepth(root.right)
                else if(root.right == null)
                    return 1+minDepth(root.left)
                else
                    return Math.min(minDepth(root.left),minDepth(root.right))+1
            }
        }
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}