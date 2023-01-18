import java.util.*

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
Medium
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T
that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [2,1], p = 2, q = 1
Output: 2

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the BST.

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val queueP = getPathFromRoot(root!!, p!!)
        val queueQ = getPathFromRoot(root!!, q!!)

        var root = queueP.peek()
        var lastAncestor = root
        queueP.poll()
        queueQ.poll()

        if(queueP.peek() == queueQ.peek()){
            lastAncestor = queueP.poll()
            queueQ.poll()
        }
        return lastAncestor
    }

    private fun getPathFromRoot(root: TreeNode, node: TreeNode):LinkedList<TreeNode>{
        var targetValue = node.`val`
        var pointer = root
        val queue = LinkedList<TreeNode>().apply{
            add(pointer)
        }
        if(pointer!=node){
            while (pointer!=node){
                if(targetValue>pointer.`val`)
                    pointer = pointer.right!!
                else if(targetValue<pointer.`val`)
                    pointer = pointer.left!!

                queue.add(pointer)
            }
        }
        return queue
    }
}