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
    private val result = mutableListOf<List<Int>>()
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        if(root!=null){
            dfs(root, mutableListOf(root.`val`), root.`val`, targetSum)
        }
        return result
    }

    fun dfs(node:TreeNode, record:MutableList<Int>, sum:Int, targetSum: Int){
        if(node.left==null && node.right==null){
            if(targetSum==sum)
                result.add(record)
        }else{
            node.left?.let {dfs(it, record.toMutableList().apply { add(it.`val`) }, sum+it.`val`, targetSum)}
            node.right?.let {dfs(it, record.toMutableList().apply { add(it.`val`) }, sum+it.`val`, targetSum)}
        }
    }
}

class SolutionSameList {
    private val result = mutableListOf<List<Int>>()
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        if(root!=null){
            dfs(root, mutableListOf(root.`val`), root.`val`, targetSum)
        }
        return result
    }

    fun dfs(node:TreeNode, record:MutableList<Int>, sum:Int, targetSum: Int){
        if(node.left==null && node.right==null){
            if(targetSum==sum)
                result.add(record.toMutableList())
        }else{
            node.left?.let {dfs(it, record.apply { add(it.`val`) }, sum+it.`val`, targetSum)}
            node.right?.let {dfs(it, record.apply { add(it.`val`) }, sum+it.`val`, targetSum)}
        }
        record.removeAt(record.lastIndex)
    }
}
