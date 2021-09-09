import java.util.*

/*
102. Binary Tree Level Order Traversal(Medium)

Share
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000

 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Solution {
    /*
    重點不難 在於怎麼處理換一個level紀錄
    這裡參考其中一個解答的最法，在迴圈外層記錄這層有幾個node，每處理完一個counter--，處理完就要重建list

    有另一個遞迴的做法，是用static的hashmap來紀錄層級別與元素，其實就是分別做左child和右child的DFS
    並且把層數帶入隨之成長，每次就是從map裡面拿出來 放入元素，最後再return map.values.toList()(蠻高招的)
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if(root == null) return listOf()

        val result:MutableList<List<Int>> = mutableListOf()
        val queue = LinkedList<TreeNode>().apply { offer(root) }

        var levelSize = 1
        while (queue.isNotEmpty()){
            val levelList = mutableListOf<Int>()
            while (levelSize>0){
                val node = queue.poll()
                node.left?.run { queue.offer(this) }
                node.right?.run { queue.offer(this) }

                levelList.add(node.`val`)
                --levelSize
            }
            result.add(levelList)
            levelSize = queue.size
        }
        return result
    }
}
fun main(args: Array<String>) {


    println("Hello World!")
}