/*
199. Binary Tree Right Side View
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.


Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:
Input: root = [1,null,3]
Output: [1,3]

Example 3:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

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
    fun rightSideView(root: TreeNode?): List<Int> {
        if(root == null)
            return mutableListOf<Int>()

        val nodeList = mutableListOf<TreeNode>().apply {
            add(root)
        }

        val result = mutableListOf<Int>().apply {
            add(root.`val`)
        }

        var headPointer = 0
        while (headPointer < nodeList.size){
            val originSize = nodeList.lastIndex - headPointer +1
            repeat(originSize){
                val node = nodeList[headPointer++]
                node.left?.let { nodeList.add(it) }
                node.right?.let { nodeList.add(it) }
            }
            result.add(nodeList.last().`val`)
        }
        return result
    }
}