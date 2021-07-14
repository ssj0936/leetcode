import sun.reflect.generics.tree.Tree
import java.util.*

/*
94. Binary Tree Inorder Traversal(Easy)

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Example 4:
Input: root = [1,2]
Output: [2,1]

Example 5:
Input: root = [1,null,2]
Output: [1,2]

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
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
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if(root==null) return listOf()
        else{
            val result = mutableListOf<Int>()
            recursive(root.left,result)
            result.add(root.`val`)
            recursive(root.right,result)
            return result
        }
    }

    fun recursive(node:TreeNode?, result:MutableList<Int>){
        if(node == null) return
        else{
            recursive(node.left,result)
            result.add(node.`val`)
            recursive(node.right,result)
        }
    }

    fun inorderTraversalIterative(root: TreeNode?): List<Int> {
        if(root==null) return listOf()
        else{
            val result = mutableListOf<Int>()
            val stack = Stack<TreeNode>().apply {
                push(root)
            }

            while (stack.isNotEmpty()){
                var node = stack.peek()
                if(node.left != null && node.left!!.`val`!=-999) stack.push(node.left)
                else{
                    result.add(node.`val`)
                    node.`val` = -999
                    stack.pop()
                    if(node.right!=null) stack.push(node.right)
                }
            }
            return result

        }
    }

    fun inorderTraversalIterativeBetter(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if(root==null) return result
        else{
            val stack = Stack<TreeNode>()
            var currentPointer:TreeNode? = root

            while (currentPointer!=null || stack.isNotEmpty()){
                while (currentPointer!=null){
                    stack.push(currentPointer)
                    currentPointer = currentPointer.left
                }
                currentPointer = stack.pop()
                result.add(currentPointer.`val`)
                currentPointer = currentPointer.right
            }
            return result

        }
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}