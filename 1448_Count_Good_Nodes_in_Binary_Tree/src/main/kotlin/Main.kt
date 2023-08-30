/**
 Example:
 var ti = TreeNode(5)
 var v = ti.`val`
 Definition for a binary tree node.
 class TreeNode(var `val`: Int) {
     var left: TreeNode? = null
     var right: TreeNode? = null
 }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    var counter = 0
    fun goodNodes(root: TreeNode?): Int {
        dfs(root, Int.MIN_VALUE)
        return counter
    }

    private fun dfs(node:TreeNode?, max:Int){
        if(node == null) return

        val newMax = maxOf(max, node.`val`)
        if(node.`val` == newMax)
            ++counter

        dfs(node.left, newMax)
        dfs(node.right, newMax)
    }
}