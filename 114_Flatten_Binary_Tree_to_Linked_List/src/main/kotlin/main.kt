import java.util.*

/*
114. Flatten Binary Tree to Linked List(Medium)

Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?

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
    pre-order traversal代表 root, left, right的順序
    用stack來記錄node拔除作業順序，每次先放右邊root 再放左邊root，然後pop出來順序放進queue中
    最後queue中就是排好的順序 最後再組裝起來就好
     */
    fun flatten(root: TreeNode?): Unit {
        if(root == null) return
        else {
            val queue : Queue<TreeNode> = LinkedList()
            val stack = Stack<TreeNode>()
            stack.push(root)
            while(stack.isNotEmpty()){
                val node = stack.pop()
                queue.offer(node)

                node.right?.let { stack.push(it) }
                node.left?.let { stack.push(it) }
            }

            var pivot = root
            queue.poll()
            while (queue.isNotEmpty()){
                pivot!!.left = null
                pivot.right = queue.poll()
                pivot = pivot.right
            }
        }
    }

    /*
    space O(1)的作法

    題目可以看到，pre-order traversal代表 root, left, right的順序，而左邊又都要是null
    所以我們每次在treversal的時候，把左右互換，原先要最後處理的的右邊被換到左邊，但我們不要掛到左邊 改掛到最右邊的葉子的地方，這樣掛其實不會影響順序
    如此達到O(1)的空間複雜度
     */
    fun flattenV2(root: TreeNode?): Unit {
        if(root == null) return
        else {
            var pivot = root
            while (pivot!=null){
                if(pivot!!.left!=null){
                    swap(pivot)
                    var runner = pivot
                    var tmp = pivot.left
                    while (runner!!.right!=null) runner = runner.right
                    runner.right = tmp
                    pivot.left = null
                }
                pivot = pivot.right
            }
        }
    }

    fun swap(node:TreeNode){
        val tmp = node.left
        node.left = node.right
        node.right = tmp
    }

}
fun main(args: Array<String>) {
    println("Hello World!")
}