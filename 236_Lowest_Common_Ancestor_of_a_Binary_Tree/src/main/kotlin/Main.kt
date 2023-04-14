import java.util.ArrayDeque

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

interface Sol{
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode?
}
class SolutionBetter:Sol{
    override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        when (root) {
            null -> return null
            p -> return p
            q -> return q
            else -> {
                val moveRootToLeft = lowestCommonAncestor(root.left, p, q)
                val moveRootToRight = lowestCommonAncestor(root.right, p, q)

                return if (moveRootToLeft != null && moveRootToRight != null)
                    root
                else if (moveRootToLeft != null)
                    moveRootToLeft
                else
                    moveRootToRight
            }
        }
    }
}

class Solution:Sol {
    private var pathP:List<TreeNode>? = null
    private var pathQ:List<TreeNode>? = null
    private var stack = ArrayDeque<TreeNode>()
    override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        dfs(root, p!!.`val`, q!!.`val`)

        var pointer = 0
        var maxSize = Math.min(pathP!!.size, pathQ!!.size)
        while (pointer<maxSize && pathP!![pointer] == pathQ!![pointer]){
            ++pointer
        }
        return pathP!![pointer-1]
    }

    private fun dfs(root: TreeNode?,pValue:Int, qValue:Int){
        if(root==null || (pathP!=null && pathQ!=null)){
            return
        }
        stack.addLast(root)

        if(root.`val` == pValue)
            pathP = stack.toList()
        else if(root.`val` == qValue)
            pathQ = stack.toList()

        dfs(root.left,pValue, qValue)
        dfs(root.right,pValue, qValue)
        stack.removeLast()
    }
}
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}