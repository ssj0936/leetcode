import java.util.*

/*
101. Symmetric Tree(Easy)

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100

Follow up: Could you solve it both recursively and iteratively?
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
    fun isSymmetricRecursive(root: TreeNode?): Boolean {
        if(root?.left == null && root?.right==null) return true
        else if(root.left == null || root.right == null) return false
        else return isMirror(root.left,root.right)
    }

    fun isMirror(p: TreeNode?, q: TreeNode?):Boolean{
        if(p == null && q == null) return true
        else if(p == null || q == null) return false
        else return (p.`val` == q.`val`) && isMirror(p.left,q.right) && isMirror(p.right,q.left)
    }

    fun isSymmetricIterative(root: TreeNode?): Boolean {
        if(root?.left == null && root?.right==null) return true
        else if(root.left == null || root.right == null) return false
        else {
            val treeLeft =root.left
            val treeRight =root.right

            val queue: Queue<TreeNode> = LinkedList()
            queue.offer(treeLeft)
            queue.offer(treeRight)
            while (queue.isNotEmpty()){
                val p1 = queue.poll()
                val p2 = queue.poll()
                if(p1 == null && p2 == null){}
                else if(p1 == null || p2 == null) return false
                else{
                    if(p1.`val` != p2.`val`){return false}
                    else{
                        queue.offer(p1.left)
                        queue.offer(p2.right)

                        queue.offer(p1.right)
                        queue.offer(p2.left)
                    }
                }
            }
        }
        return true
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}