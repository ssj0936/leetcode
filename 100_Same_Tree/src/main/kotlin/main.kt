import java.util.*

/*
100. Same Tree(Easy)

Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false

Constraints:

The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104

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
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if(p==null && q==null) return true
        if(p == null || q == null) return false
        return (p?.`val`== q?.`val`) && isSameTree(p?.left,q?.left) && isSameTree(p?.right,q?.right)
    }

    //遞迴解
    //簡單來說 就是每個比對組 依序排隊入列
    //每次push兩個，然後pop兩個來比對，如果val一樣 那就左邊兩個 右邊兩個 依次入列等待受檢
    fun isSameTree2(p: TreeNode?, q: TreeNode?): Boolean {
        if(p==null && q==null) return true
        if(p == null || q == null) return false

        val queue:Queue<TreeNode> = LinkedList()
        queue.offer(p)
        queue.offer(q)
        while(queue.isNotEmpty()){
            val pp = queue.poll()
            val qq = queue.poll()
            if(pp==null && qq==null){}
            else if(pp == null || qq == null) return false

            if(pp.`val`!=qq.`val`) return false
            else{
                if(pp.left==null && qq.left==null){}
                else if(pp == null || qq == null)
                    return false
                else {
                    queue.offer(pp.left)
                    queue.offer(qq.left)
                }

                if(pp.right==null && qq.right==null){}
                else if(pp == null || qq == null)
                    return false
                else {
                    queue.offer(pp.right)
                    queue.offer(qq.right)
                }
            }
        }
        return true
    }
}

fun main(args: Array<String>) {
    println("Hello World!")
}