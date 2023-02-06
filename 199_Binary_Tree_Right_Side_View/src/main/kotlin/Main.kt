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

interface Sol{
    fun rightSideView(root: TreeNode?): List<Int>
}

class SolutionIterative:Sol {
    override fun rightSideView(root: TreeNode?): List<Int> {
        if(root == null)
            return mutableListOf<Int>()

        var nodeList = mutableListOf<TreeNode>().apply {
            add(root)
        }

        val result = mutableListOf<Int>().apply {
            add(root.`val`)
        }

        while (nodeList.isNotEmpty()){
            val newNodeList = mutableListOf<TreeNode>()
            for(node in nodeList){
                node.left?.let { newNodeList.add(it) }
                node.right?.let { newNodeList.add(it) }
            }
            nodeList = newNodeList
            if(nodeList.isNotEmpty())
                result.add(nodeList.last().`val`)
        }
        return result
    }
}

class SolutionRecursive:Sol {
    private val result = mutableListOf<Int>()
    override fun rightSideView(root: TreeNode?): List<Int> {
        if(root == null)
            return listOf()

        foo(listOf(root))
        return result
    }

    private fun foo(list: List<TreeNode>){
        if(list.isEmpty())
            return

        result.add(list.last().`val`)
        val newList = mutableListOf<TreeNode>()
        for(node in list){
            node.left?.let { newList.add(it) }
            node.right?.let { newList.add(it) }
        }
        foo(newList)
    }
}

class SolutionRecursiveV2:Sol {
    private val result = mutableListOf<Int>()

    override fun rightSideView(root: TreeNode?): List<Int> {
        foo(root, 0)
        return result
    }

    private fun foo(root: TreeNode?, depth:Int) {
        if(root == null)
            return

        if(result.size == depth)
            result.add(root.`val`)

        if(root.right!=null)
            foo(root.right, depth+1)
        if(root.left!=null)
            foo(root.left, depth+1)
    }
}