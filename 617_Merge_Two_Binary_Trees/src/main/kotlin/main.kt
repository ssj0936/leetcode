import java.util.*

/*
617. Merge Two Binary Trees(Easy)

You are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.

Example 1:
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]

Example 2:
Input: root1 = [1], root2 = [1,2]
Output: [2,2]

Constraints:

The number of nodes in both trees is in the range [0, 2000].
-10^4 <= Node.val <= 10^4

 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

interface Sol{
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode?
}

/*
遞迴解

相當直觀應該不用解說
 */
class Solution: Sol{
    override fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        return foo(root1, root2)
    }

    private fun foo(nodeFromTreeA:TreeNode?,nodeFromTreeB:TreeNode?):TreeNode?{
        if(nodeFromTreeA == null) return nodeFromTreeB
        if(nodeFromTreeB == null) return nodeFromTreeA

        return TreeNode(nodeFromTreeA.`val` + nodeFromTreeB.`val`).apply {
            left = foo(nodeFromTreeA.left, nodeFromTreeB.left)
            right = foo(nodeFromTreeA.right, nodeFromTreeB.right)
        }
    }
}


/*
迭代解

binary tree要loop though 首用stack
跟遞迴解不一樣，沿用原tree1的點，在tree1上做計算
順序應該沒差 這便用的是Preorder
先處理父節點 在處理兩個子傑點

如果node1是null而node2不是的話，直接接嫁過來
 */
class Solution2: Sol{
    override fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if(root1 == null) return root2
        if(root2 == null) return root1

        val stack = Stack<Pair<TreeNode,TreeNode>>().apply {
            push(root1 to root2)
        }

        while (stack.isNotEmpty()){
            val (node1, node2) = stack.pop()
            node1.`val` += node2.`val`

            if(node1.left != null && node2.left !=null)
                stack.push(node1.left!! to node2.left!!)
            else if(node2.left != null)
                node1.left = node2.left

            if(node1.right != null && node2.right !=null)
                stack.push(node1.right!! to node2.right!!)
            else if (node2.right != null)
                node1.right = node2.right
        }

        return root1
    }
}


fun main(args: Array<String>) {
    println("Hello World!")
}