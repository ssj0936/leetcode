fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
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
    var max = 0
    fun longestZigZag(root: TreeNode?): Int {
        if(root==null)
            return max
//        maxZigZag(root, DIRECTION_LEFT)
        maxZigZag(root, DIRECTION_LEFT,0)
        maxZigZag(root, DIRECTION_RIGHT,0)

        return max
    }

    private val DIRECTION_LEFT = 0
    private val DIRECTION_RIGHT = 1
    private fun maxZigZag(node:TreeNode, direction:Int):Int{
        //node第一步走左邊
        var resultLeft = if(node.left==null) 0 else 1 + maxZigZag(node.left!!, DIRECTION_RIGHT)
        //node第一步走右邊
        var resultRight = if(node.right==null) 0 else 1 + maxZigZag(node.right!!, DIRECTION_LEFT)

        max = maxOf(maxOf(resultRight, resultLeft), max)

        return if(direction == DIRECTION_LEFT) resultLeft else resultRight
    }

    private fun maxZigZag(node:TreeNode?, direction:Int, length:Int = 0){
        if(node == null) {
            max = maxOf(max, length - 1)
            return
        }

        if(direction == DIRECTION_LEFT) {
            maxZigZag(node.left, DIRECTION_RIGHT, length + 1)
            maxZigZag(node.right, DIRECTION_LEFT, 1)
        }else{
            maxZigZag(node.right, DIRECTION_LEFT, length + 1)
            maxZigZag(node.left, DIRECTION_RIGHT, 1)
        }
    }
}