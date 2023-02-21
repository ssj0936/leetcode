import java.util.Stack

/*
230. Kth Smallest Element in a BST
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104


Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?


 */
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
    var count = 0
    var result = -1
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if(root==null)
            return result
        foo(root,k)
        return result
    }

    private fun foo(root: TreeNode, k: Int){
        if(result!=-1) return

        if(root.left==null && root.right==null){
            ++count
            if(count==k) {
                result = root.`val`
                return
            }
        }else{
            if(root.left!=null)
                foo(root.left!!, k)
            ++count
            if(count==k) {
                result = root.`val`
                return
            }
            if(root.right!=null)
                foo(root.right!!, k)
        }
    }

}
class SolutionRecursive {
    var count = 0
    var result = -1
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if(root==null)
            return result
        foo(root,k)
        return result
    }

    private fun foo(root: TreeNode?, k: Int){
        if(root==null || result!=-1) return

        foo(root.left, k)
        ++count
        if(count==k) {
            result = root.`val`
            return
        }
        foo(root.right, k)
    }
}

class SolutionIterative {
    var count = 0
    var result = -1
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if(root==null)
            return result

        //stack
        val stack = ArrayDeque<TreeNode>()
        var pointer = root
        while (true){
            while (pointer!!.left != null){
                stack.addLast(pointer)
                pointer = pointer.left
            }

            pointer = stack.removeLast()
            println(pointer.`val`)
            ++count
            if(count == k){
                result = pointer.`val`
                break
            }
            pointer = pointer.right
        }
        return result
    }
}