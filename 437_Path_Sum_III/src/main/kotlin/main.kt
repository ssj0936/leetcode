import java.util.*

/*
437. Path Sum III(Medium)
Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

Example 1:
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.

Example 2:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-10^9 <= Node.val <= 10^9
-1000 <= targetSum <= 1000
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
    /*
    直觀的暴力法

    用DFS去找出以每個點為起點的path，DFS期間不提早return 以防有回馬槍後面總和又是0的狀況
    DFS會把剩餘的值帶進下一輪
     */

    var counter = 0
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if(root == null) return counter
        val stack = Stack<TreeNode>().apply { push(root) }

        while (stack.isNotEmpty()){
            val thisOne = stack.pop()
            thisOne.left?.let { stack.push(it) }
            thisOne.right?.let { stack.push(it) }

            dfs(thisOne,targetSum)
        }
        return counter
    }

    private fun dfs(node:TreeNode, target:Int){
        val remain = target - node.`val`
        if(remain==0) ++counter

        node.left?.let{dfs(it,remain)}
        node.right?.let{dfs(it,remain)}
    }
}

fun main(args: Array<String>) {
    println("Hello World!")
}